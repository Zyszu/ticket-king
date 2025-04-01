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
import pl.matzysz.service.EmailSenderService;
import pl.matzysz.service.UserService;
import pl.matzysz.validator.UserValidator;

@Controller
@RequestMapping("/register-user")
public class RegisterUserController {

    private final UserService userService;
    private final UserValidator userValidator = new UserValidator();
    private final EmailSenderService emailSenderService;

    public RegisterUserController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());
        return "register-user";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            model.addAttribute("messageError", "error.user.email.exists");
            return  "register-user";
        }

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register-user";
        }

        // emailSenderService.sendEmail(user.getEmail(), "Welcom on board!", "Hi, thanks you for registering in ticket king!");

        userService.addUser(user);
        return "redirect:/login";
    }

}
