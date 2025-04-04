package pl.matzysz.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.matzysz.domain.*;
import pl.matzysz.domain.DTO.TicketWrapperDTO;
import pl.matzysz.service.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/tickets")
public class TicketController {

    private final FlightService flightService;
    private final UserService userService;
    private final CompanyService companyService;
    private final TicketService ticketService;
    private final PurchaseService purchaseService;

    public TicketController(
            FlightService flightService,
            UserService userService,
            CompanyService companyService,
            TicketService ticketService,
            PurchaseService purchaseService) {
        this.flightService = flightService;
        this.userService = userService;
        this.companyService = companyService;
        this.ticketService = ticketService;
        this.purchaseService = purchaseService;
    }

    @GetMapping(value = "/purchase/{flightId}")
    public String buyTicket(
            @PathVariable("flightId") Long flightId,
            Model model,
            Principal principal,
            RedirectAttributes  redirectAttributes
            ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        Flight flight = flightService.getFlight(flightId);
        if (flight == null) {
            return "redirect:/home"; // + errors
        }

        long companyId = flight.getAircraft().getCompany().getId();
        Company company = companyService.getCompany(companyId);
        if (company == null) {
            return "redirect:/home"; // + errors
        }

        if (user.getCompany() != null) {
            if (company.getId() == user.getCompany().getId()) {
                redirectAttributes.addFlashAttribute("messageError", "error.can.not.buy.yours.ticket");
                return "redirect:/home";
            }
        }

        Integer ticketCount = flight.getTicketList().size();
        flight.setTicketList(null);

        model.addAttribute("flight", flight);
        model.addAttribute("seatsTaken", ticketCount);
        return "buy-ticket";
    }

    @GetMapping(value = "/wallet")
    public String wallet(
            Model model,
            Principal principal
    ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        // this should be using dedicated DTO
        // in current form pretty departure date and time view
        // is either hard or overcomplicated
        List<Ticket> ticketList = ticketService.getTicketsByUser(user);
        List<Ticket> activeTicketList = new ArrayList<>(0);
        for (Ticket ticket : ticketList) {
            if (ticket.isPaid()) activeTicketList.add(ticket);
        }

        model.addAttribute("ticketList", activeTicketList);
        return "wallet";
    }

    @PostMapping(value = "/purchase")
    public String purchase(
            @ModelAttribute("flightId") Long flightId,
            @ModelAttribute("ticketCount") Integer purchaseTicketCount,
            Model model,
            Principal principal,
            RedirectAttributes  redirectAttributes
    ) {
        Flight flight = flightService.getFlight(flightId);
        if (flight == null) {
            return "redirect:/home"; // + errors
        }

        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        Company company = companyService.getCompany(flight.getAircraft().getCompany().getId());
        if (company == null) {
            return "redirect:/home"; // + errors
        }

        if (user.getCompany() != null) {
            if (company.getId() == user.getCompany().getId()) {
                redirectAttributes.addFlashAttribute("messageError", "error.can.not.buy.yours.ticket");
                return "redirect:/home";
            }
        }

        // check if there are enough empty seats left
        int ticketCount = flight.getTicketList().size();
        if (ticketCount + purchaseTicketCount > flight.getAvailableSeats()) {
            return "redirect:/home"; // + errors [not enough tickets left]
        }

        List<Ticket> listTicket = new ArrayList<>(0);

        for (int i = 0; i < purchaseTicketCount; i++) {
            Ticket ticket = new Ticket();
            ticket.setFlight(flight);
            ticket.setUser(user);
            ticket.setCompany(company);
            ticket.setIssuedAt(LocalDateTime.now());
            ticket.setPaid(false);
            listTicket.add(ticket);
        }

        List<Long> listTicketId        = new ArrayList<>(0);
        List<Ticket> createdTicketList = ticketService.createManyTickets(listTicket);
        createdTicketList.forEach(ticket -> { listTicketId.add(ticket.getId()); });

        ticketService.createManyTickets(listTicket);
        purchaseService.startPaymentWindow(listTicketId);

        TicketWrapperDTO ticketWrapperDTO = new TicketWrapperDTO();
        ticketWrapperDTO.setTicketIds(listTicketId);

        redirectAttributes.addFlashAttribute("ticketWrapperDTO", ticketWrapperDTO);
        return "redirect:/tickets/purchase/confirm";
    }

    @GetMapping("/purchase/confirm")
    public String confirmPaymentPage(
            Model model
    ) {
        TicketWrapperDTO ticketWrapperDTO = (TicketWrapperDTO) model.getAttribute("ticketWrapperDTO");
        model.addAttribute("ticketWrapperDTO", ticketWrapperDTO);
        return "confirm-payment";
    }

    @PostMapping("/purchase/confirm")
    public String confirmPayment(
            @ModelAttribute("ticketWrapperDTO") TicketWrapperDTO ticketWrapperDTO,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {
        List<Long> listTicketId = ticketWrapperDTO.getTicketIds();

        User currentUser = userService.getUserByEmail(principal.getName());
        List<Ticket> usersTickets = ticketService.getTicketsByUser(currentUser);
        List<Long> listUserTicketId = new ArrayList<>(0);
        usersTickets.forEach(ticket -> { listUserTicketId.add(ticket.getId()); });

        for (Long ticketId : listTicketId) {
            if (!listUserTicketId.contains(ticketId)) {
                redirectAttributes.addFlashAttribute("messageError", "error.can.not.be.yours.ticket");
                return "redirect:/home";
            }
        }


        purchaseService.confirmPayment(listTicketId);
        redirectAttributes.addFlashAttribute("messageInfo", "info.payment.confirmed");
        return "redirect:/home";
    }

    @GetMapping("/pdf/{ticketId}")
    public void pdf(
            @PathVariable("ticketId") Long ticketId,
            Principal principal,
            HttpServletResponse response
    ) throws Exception {

        // Check if ticket exists
        Ticket ticket = ticketService.getTicketWithDetails(ticketId);
        if (ticket == null) {
            response.sendRedirect(response.encodeRedirectURL("/access-denied"));
            return;
        }

        // Check if ticket belongs to the requesting pdf user
        User user = userService.getUserByEmail(principal.getName());
        if (ticket.getUser().getId() != user.getId()) {
            response.sendRedirect(response.encodeRedirectURL("/access-denied"));
            return;
        }


        // Generate ticket info string
        String ticketInfo = String.format(
                "Ticket ID: %d%nFlight: %s → %s%nCarrier: %s%nAircraft: %s%nDeparture: %s",
                ticket.getId(),
                ticket.getFlight().getAirfieldFrom(),
                ticket.getFlight().getAirfieldTo(),
                ticket.getCompany().getCompanyName(),
                ticket.getFlight().getAircraft().getModel(),
                ticket.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );



        // Setup response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ticket-" + ticketId + ".pdf");

        // Generate PDF with QR code
        generatePdfWithQrCode(response.getOutputStream(), ticketInfo);
    }

    private void generatePdfWithQrCode(ServletOutputStream out, String qrContent) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        // Add ticket info as text
        document.add(new Paragraph("Ticket Confirmation", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(qrContent));
        document.add(new Paragraph(" "));

        // Generate QR code image
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        Image qrPdfImage = Image.getInstance(baos.toByteArray());

        document.add(qrPdfImage);
        document.close();
    }

}
