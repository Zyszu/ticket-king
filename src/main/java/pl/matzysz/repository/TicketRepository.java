package pl.matzysz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.Ticket;
import pl.matzysz.domain.User;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);

    @Query("SELECT t FROM Ticket t JOIN FETCH t.flight JOIN FETCH t.company WHERE t.user.id = :userId")
    List<Ticket> findAllTicketsByUserWithDetails(@Param("userId") Long userId);

}
