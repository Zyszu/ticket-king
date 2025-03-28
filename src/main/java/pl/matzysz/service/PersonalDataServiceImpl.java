package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.PersonalData;
import pl.matzysz.repository.PersonalDataRepository;

import java.util.List;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    final PersonalDataRepository personalDataRepository;

    @Autowired
    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }


    @Transactional
    public void addPersonalData(PersonalData personalData) {
        personalDataRepository.save(personalData);
    }

    @Transactional
    public void editPersonalData(PersonalData personalData) {
        personalDataRepository.save(personalData);
    }

    @Transactional
    public List<PersonalData> listPersonalData() {
        return personalDataRepository.findAll();
    }

    @Transactional
    public void deletePersonalData(long id) {
        personalDataRepository.deleteById(id);
    }

    @Transactional
    public PersonalData getPersonalData(long id) {
        return personalDataRepository.findById(id);
    }

}
