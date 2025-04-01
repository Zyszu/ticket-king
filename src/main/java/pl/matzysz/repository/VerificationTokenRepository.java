package pl.matzysz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matzysz.domain.User;
import pl.matzysz.domain.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);

}
