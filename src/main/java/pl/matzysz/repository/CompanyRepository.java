package pl.matzysz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.Company;

@Transactional
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyName(String name);
    Company findById(long id);
}
