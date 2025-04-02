package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.matzysz.domain.PersonalData;
import pl.matzysz.domain.User;
import pl.matzysz.service.PersonalDataService;
import pl.matzysz.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/personal-data")
public class PersonalDataController {

    private final PersonalDataService personalDataService;
    private final UserService userService;

    public PersonalDataController(PersonalDataService personalDataService, UserService userService) {
        this.personalDataService = personalDataService;
        this.userService = userService;
    }

    @GetMapping
    public String showForm(
            Model model,
            Principal principal
    ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        if (user.getPersonalData() != null) {
            model.addAttribute("personalData", user.getPersonalData());
        } else {
            model.addAttribute("personalData", new PersonalData());
        }

        return "personal-data";
    }

    @PostMapping
    public String savePersonalData(
            @ModelAttribute("personalData") PersonalData personalData,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {

        User user = userService.getUserByEmail(principal.getName());
        if ( user == null) {
            return "redirect:/home"; // + errors
        }

        user.setPersonalData(personalData);
        userService.editUser(user);

        redirectAttributes.addFlashAttribute("messageInfo", "info.personal.data.controller.success");
        return "redirect:/home";
    }
}
