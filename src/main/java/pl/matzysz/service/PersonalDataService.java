package pl.matzysz.service;

import pl.matzysz.domain.PersonalData;

import java.util.List;

public interface PersonalDataService {
    public void addPersonalData(PersonalData personalData);
    public void editPersonalData(PersonalData personalData);
    public List<PersonalData> listPersonalData();
    public void deletePersonalData(long id);
    public PersonalData getPersonalData(long id);
}
