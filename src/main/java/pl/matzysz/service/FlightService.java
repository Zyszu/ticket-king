package pl.matzysz.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.matzysz.domain.Flight;

import java.util.List;

public interface FlightService {
    public void addFlight(Flight flight);
    public void editFlight(Flight flight);
    public List<Flight> listFlight();
    public void deleteFlight(long id);
    public Flight getFlight(long id);
}
