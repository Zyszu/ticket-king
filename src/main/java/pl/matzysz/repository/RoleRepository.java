package pl.matzysz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matzysz.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String name);

}
