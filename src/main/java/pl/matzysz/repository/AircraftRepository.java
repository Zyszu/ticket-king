package pl.matzysz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.Aircraft;

import java.util.List;

@Transactional
@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    Aircraft findById(long id);

}
