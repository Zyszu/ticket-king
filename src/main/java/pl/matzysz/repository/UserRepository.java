package pl.matzysz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.User;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(long id);
}
