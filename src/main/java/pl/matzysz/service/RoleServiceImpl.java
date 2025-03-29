package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Role;
import pl.matzysz.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    public Optional<Role> getRole(long id) {
        return roleRepository.findById(id);
    }

}
