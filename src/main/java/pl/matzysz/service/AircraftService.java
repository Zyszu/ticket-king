package pl.matzysz.service;

import pl.matzysz.domain.Aircraft;

import java.util.List;

public interface AircraftService {

    public Aircraft addAircraft(Aircraft aircraft);
    public void editAircraft(Aircraft aircraft);
    public List<Aircraft> listAircraft();
    public void deleteAircraft(long id);
    public Aircraft getAircraft(long id);

}
