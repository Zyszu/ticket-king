package pl.matzysz.service;

import pl.matzysz.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public Role addRole(Role role);
    public Role updateRole(Role role);
    public List<Role> listRoles();
    public void deleteRole(long id);
    public Optional<Role> getRole(long id);
}
