package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.Flight;
import pl.matzysz.domain.Ticket;
import pl.matzysz.domain.User;
import pl.matzysz.repository.TicketRepository;
import pl.matzysz.repository.UserRepository;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.FlightService;
import pl.matzysz.service.TicketService;
import pl.matzysz.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/tickets")
public class TicketController {

    private final FlightService flightService;
    private final UserService userService;
    private final CompanyService companyService;
    private final TicketService ticketService;

    public TicketController(
            FlightService flightService,
            UserService userService,
            CompanyService companyService,
            TicketService ticketService
    ) {
        this.flightService = flightService;
        this.userService = userService;
        this.companyService = companyService;
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/purchase/{flightId}")
    public String buyTicket(
            @PathVariable("flightId") Long flightId,
            Model model,
            Principal principal
            ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        Flight flight = flightService.getFlight(flightId);
        if (flight == null) {
            return "redirect:/home"; // + errors
        }

        Company company = companyService.getCompany(flight.getAircraft().getCompany().getId());
        if (company == null) {
            return "redirect:/home"; // + errors
        }

        if (company.getOwner().equals(user)) {
            return "redirect:/home"; // + errors [proprietor can't buy ticket for own flight]
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

        Set<Ticket> ticketList = user.getTicketList();
        model.addAttribute("ticketList", ticketList);

        return "wallet";
    }

    @PostMapping(value = "/purchase")
    public String purchase(
            @ModelAttribute("flightId") Long flightId,
            @ModelAttribute("ticketCount") Integer purchaseTicketCount,
            Model model,
            Principal principal
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

        if (company.getOwner().equals(user)) {
            return "redirect:/home"; // + errors [proprietor can't buy ticket for own flight]
        }

        // check if there are enough empty seats left
        int ticketCount = flight.getTicketList().size();
        if (ticketCount + purchaseTicketCount > flight.getAvailableSeats()) {
            return "redirect:/home"; // + errors [not enough tickets left]
        }

        List<Ticket> ticketList = new ArrayList<>(0);

        for (int i = 0; i < purchaseTicketCount; i++) {
            Ticket ticket = new Ticket();
            ticket.setFlight(flight);
            ticket.setUser(user);
            ticket.setCompany(company);

            ticketList.add(ticket);
        }

        ticketService.createManyTickets(ticketList);

        return "redirect:/tickets/purchase/" + flightId;
    }

}
