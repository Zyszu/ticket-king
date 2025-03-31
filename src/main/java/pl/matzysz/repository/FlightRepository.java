package pl.matzysz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.Flight;
import pl.matzysz.domain.User;

import java.util.List;

@Transactional
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findById(long id);

}
