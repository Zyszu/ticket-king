package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Aircraft;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.Flight;
import pl.matzysz.domain.User;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.FlightService;
import pl.matzysz.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final CompanyService companyService;
    private final FlightService flightService;
    private final UserService userService;

    public FlightController(
            CompanyService companyService,
            FlightService flightService,
            UserService userService
    ) {
        this.companyService = companyService;
        this.flightService = flightService;
        this.userService = userService;
    }

    @GetMapping
    public String showCreateFlightForm(
            Model model,
            Principal principal
    ) {
        User user = userService.getUserByEmail(principal.getName());
        if ( user == null ) {
            return "redirect:/home"; // + errors
        }

        Company company = user.getCompany();
        if ( company == null ) {
           return "redirect:/home"; // + errors
        }

        Set<Aircraft> aircraftList = company.getAircraftList();

        model.addAttribute("flight", new Flight());
        model.addAttribute("aircraftList", aircraftList);

        return "flight";
    }

    @PostMapping
    public String createFlight(
            @ModelAttribute("flight") Flight flight,
            Principal principal
    ) {
        System.out.println(
                "Creating flight " + flight.getId()
                + " from " + flight.getAirfieldFrom()
                + " to " + flight.getAirfieldTo()
        );

        flightService.addFlight(flight);
        return "redirect:/flights";
    }
}
