package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.User;
import pl.matzysz.repository.RoleRepository;
import pl.matzysz.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addUser(User user) {
        user.getRoles().add(roleRepository.findByRole("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUser(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
