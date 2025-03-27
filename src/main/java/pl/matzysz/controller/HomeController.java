package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.matzysz.domain.Flight;
import pl.matzysz.service.FlightService;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final FlightService flightService;

    public HomeController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String index(Model model) {
        List<Flight> flightList = flightService.listFlight();
        model.addAttribute("flightList", flightList);

        return "home";
    }

}
