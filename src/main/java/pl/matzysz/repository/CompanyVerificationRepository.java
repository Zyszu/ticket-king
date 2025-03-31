package pl.matzysz.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.CompanyVerification;

import java.util.List;

public interface CompanyVerificationRepository extends JpaRepository<CompanyVerification, Long> {

    List<CompanyVerification> findByVerificationComplete(@NotNull boolean verificationComplete);
    List<CompanyVerification> findByCompany(@NotNull Company company);
}
