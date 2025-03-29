package pl.matzysz.configuration;

import org.springframework.context.annotation.Configuration;
import pl.matzysz.domain.Role;
import pl.matzysz.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;


// A configuration class ment to create essential for app roles in DB
@Configuration
public class RoleInitializer {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.initRoles();
    }

    private void initRoles() {

        String ROLE_USER = "ROLE_USER";
        String ROLE_PROPRIETOR = "ROLE_PROPRIETOR";
        String ROLE_SUPPORT = "ROLE_SUPPORT";

        List<String> roles = new ArrayList<>();

        roles.add(ROLE_USER);
        roles.add(ROLE_PROPRIETOR);
        roles.add(ROLE_SUPPORT);

        // check every essential role listed above
        // if a role doesn't exist -> create it
        for (String role : roles) {
            if (roleRepository.findByRole(role) == null) {
                Role newRole = new Role();
                newRole.setRole(role);

                roleRepository.save(newRole);
            }
        }

    }

}
