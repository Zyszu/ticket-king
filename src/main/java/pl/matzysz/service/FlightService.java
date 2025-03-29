package pl.matzysz.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.matzysz.domain.Flight;

import java.util.List;

@PreAuthorize("hasRole('PROPRIETOR')")
public interface FlightService {
    public void addFlight(Flight flight);
    public void editFlight(Flight flight);

    @PreAuthorize("permitAll()")
    public List<Flight> listFlight();

    public void deleteFlight(long id);
    public Flight getFlight(long id);
}
