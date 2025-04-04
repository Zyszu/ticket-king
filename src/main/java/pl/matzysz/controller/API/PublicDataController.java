package pl.matzysz.controller.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.matzysz.domain.DTO.FlightDTO;
import pl.matzysz.domain.Flight;
import pl.matzysz.service.FlightService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PublicDataController {

    private final FlightService flightService;

    public PublicDataController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<FlightDTO> getFlights() {
        List<Flight> listFlight = flightService.listFlight();
        List<FlightDTO> listFlightDTO = new ArrayList<>(0);
        listFlight.forEach(flight -> listFlightDTO.add(new FlightDTO(flight)));
        return listFlightDTO;
    }

}
