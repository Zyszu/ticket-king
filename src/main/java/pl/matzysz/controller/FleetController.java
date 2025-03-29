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
import pl.matzysz.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("fleet")
public class FleetController {

    private final CompanyService companyService;
    private final UserService userService;
    private final AircraftService aircraftService;

    public FleetController(
            CompanyService companyService,
            UserService userService, AircraftService aircraftService) {
        this.companyService = companyService;
        this.userService = userService;
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public String index(
            Model model,
            Principal principal
    ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // for now! [change later]
        }

        Company company = user.getCompany();
        if (company == null) {
            return "redirect:/home"; // for now! [change later]
        }

        model.addAttribute("aircraftList", company.getAircraftList());
        model.addAttribute("aircraft", new Aircraft()); // Aircraft instance
        return "fleet";
    }


    @PostMapping
    public String registerAircraft(
            @ModelAttribute("aircraft") Aircraft aircraft,
            Principal principal,
            BindingResult bindingResult,
            Model model
    ) {
        User user = userService.getUserByEmail(principal.getName());

        if (user == null) {
            return "redirect:/home"; // + errors
        }

        Company company = user.getCompany();
        if (company == null) {
            return "redirect:/home"; // + errors
        }
        aircraft.setCompany(company);

        aircraftService.addAircraft(aircraft);
        return "redirect:/fleet";
    }


}
