package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Aircraft;
import pl.matzysz.repository.AircraftRepository;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Transactional
    public Aircraft addAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Transactional
    public void editAircraft(Aircraft aircraft) {
        aircraftRepository.save(aircraft);
    }

    @Transactional
    public List<Aircraft> listAircraft() {
        return aircraftRepository.findAll();
    }

    @Transactional
    public void deleteAircraft(long id) {
        aircraftRepository.deleteById(id);
    }

    @Transactional
    public Aircraft getAircraft(long id) {
        return aircraftRepository.findById(id);
    }

}
