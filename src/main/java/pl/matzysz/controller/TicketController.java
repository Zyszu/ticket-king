package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.Flight;
import pl.matzysz.domain.User;
import pl.matzysz.repository.UserRepository;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.FlightService;
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
    private final UserRepository userRepository;

    public TicketController(
            FlightService flightService,
            UserService userService,
            CompanyService companyService,
            UserRepository userRepository) {
        this.flightService = flightService;
        this.userService = userService;
        this.companyService = companyService;
        this.userRepository = userRepository;
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

        Integer passengersCount = flight.getPassengers().size();
        flight.setPassengers(null);

        model.addAttribute("flight", flight);
        model.addAttribute("seatsTaken", passengersCount);
        return "buy-ticket";
    }

    @GetMapping(value = "/wallet")
    public String wallet(
            Model model,
            Principal principal
    ) {
        return "wallet";
    }

    @PostMapping(value = "/purchase")
    public String purchase(
            @ModelAttribute("flightId") Long flightId,
            @ModelAttribute("ticketCount") Integer ticketCount,
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
        Integer passengersCount = flight.getPassengers().size();
        if (passengersCount + ticketCount > flight.getAvailableSeats()) {
            return "redirect:/home"; // + errors [not enough tickets left]
        }

        for (int i = 0; i < ticketCount; i++) {
            user.getFlights().add(flight);
        }
        userService.editUser(user);
        return "redirect:/tickets/purchase/" + flightId;
    }

}
