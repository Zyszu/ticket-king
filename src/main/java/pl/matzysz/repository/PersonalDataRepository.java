package pl.matzysz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.PersonalData;

@Transactional
@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    PersonalData findById(long id);
}
