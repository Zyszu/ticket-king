package pl.matzysz.controller;

import jakarta.servlet.http.HttpServletRequest;
import pl.matzysz.domain.Address;
import pl.matzysz.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.matzysz.repository.UserRepository;
import pl.matzysz.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        User user = new User();
        user.setAddress(new Address());
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Debugging output
        System.out.println(
                "Email: " + user.getEmail() +
                        " | Country: " + user.getAddress().getCountry() +
                        " | State: " + user.getAddress().getState() +
                        " | City: " + user.getAddress().getCity() +
                        " | ZipCode: " + user.getAddress().getZipCode()
        );

        userService.addUser(user);
        return "redirect:register";
    }

}
