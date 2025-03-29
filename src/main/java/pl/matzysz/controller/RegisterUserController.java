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
import pl.matzysz.service.UserService;

@Controller
@RequestMapping("/register-user")
public class RegisterUserController {

    private final UserService userService;

    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());
        return "register-user";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register-user";
        }

        userService.addUser(user);
        return "redirect:/login";
    }

}
