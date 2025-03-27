package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Aircraft;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.Flight;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.FlightService;

import java.util.Set;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final CompanyService companyService;
    private final FlightService flightService;

    public FlightController(CompanyService companyService, FlightService flightService) {
        this.companyService = companyService;
        this.flightService = flightService;
    }

    @GetMapping
    public String showCreateFlightForm(Model model) {
        Company company = companyService.getCompany(1L); // always company id=1
        Set<Aircraft> aircraftList = company.getAircraftList();

        model.addAttribute("flight", new Flight());
        model.addAttribute("aircraftList", aircraftList);

        return "flight";
    }

    @PostMapping
    public String createFlight(@ModelAttribute("flight") Flight flight) {
        flightService.addFlight(flight);
        return "redirect:/flights";
    }
}
