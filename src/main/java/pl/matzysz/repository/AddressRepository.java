package pl.matzysz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matzysz.domain.Address;

@Transactional
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    public Address findById(long id);

}
