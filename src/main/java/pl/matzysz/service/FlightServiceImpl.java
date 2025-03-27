package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Flight;
import pl.matzysz.repository.FlightRepository;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Transactional
    public void editFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Transactional
    public List<Flight> listFlight() {
        return flightRepository.findAll();
    }

    @Transactional
    public void deleteFlight(long id) {
        flightRepository.deleteById(id);
    }

    @Transactional
    public Flight getFlight(long id) {
        return flightRepository.findById(id);
    }

}
