package pl.matzysz.service;

import org.springframework.web.bind.annotation.ModelAttribute;
import pl.matzysz.domain.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);
    public void editUser(User user);
    public List<User> listUser();
    public void removeUser(long id);
    public User getUser(long id);
    public User getUserByEmail(String email);

}
