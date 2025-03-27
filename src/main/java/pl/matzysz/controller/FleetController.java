package pl.matzysz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Aircraft;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.User;
import pl.matzysz.service.AircraftService;
import pl.matzysz.service.CompanyService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("fleet")
public class FleetController {

    private final AircraftService aircraftService;
    private final CompanyService companyService;

    public FleetController(
            AircraftService aircraftService,
            CompanyService companyService
        ) {
        this.aircraftService = aircraftService;
        this.companyService = companyService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("aircraft", new Aircraft()); // Aircraft instance
        return "fleet";
    }

    @GetMapping("/{companyId}")
    public String listAircraft(@PathVariable("companyId") long companyId, Model model) {
        Company company = companyService.getCompany(companyId);

        if (company == null) {
            return "redirect:/fleet";
        }

        Set<Aircraft> aircraftList = company.getAircraftList();
        model.addAttribute("aircraftList", aircraftList);

        Aircraft aircraft = new Aircraft();
        aircraft.setCompanyId(companyId);

        model.addAttribute("aircraft", aircraft);
        return "fleet";
    }

    @PostMapping
    public String registerAircraft(@ModelAttribute("aircraft") Aircraft aircraft) {
        Long companyId = aircraft.getCompanyId(); // Now it's bound directly

        Company company = companyService.getCompany(companyId);

        if (company != null) {
            // Save the aircraft if needed (or cascade save via company)
            company.getAircraftList().add(aircraft);
            companyService.editCompany(company);
        }

        return "redirect:/fleet/" + companyId;
    }


}
