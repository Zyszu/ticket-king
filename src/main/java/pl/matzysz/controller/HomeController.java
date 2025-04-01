package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.matzysz.domain.DTO.FlightDTO;
import pl.matzysz.domain.Flight;
import pl.matzysz.service.FlightService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    private final FlightService flightService;

    public HomeController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String index(Model model) {
        List<Flight> listFlight = flightService.listFlight();
        List<FlightDTO> listFlightDTO = new ArrayList<>(0);
        for (Flight flight : listFlight) {
            listFlightDTO.add(new FlightDTO(flight));
        }

        model.addAttribute("listFlightDTO", listFlightDTO);
        return "home";
    }

}
