package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.PersonalData;
import pl.matzysz.domain.User;
import pl.matzysz.service.PersonalDataService;
import pl.matzysz.service.UserService;

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
    public String showForm(Model model) {
        model.addAttribute("personalData", new PersonalData());
        return "personal-data";
    }

    @PostMapping
    public String savePersonalData(@ModelAttribute("personalData") PersonalData personalData,
                                   @RequestParam("userId") Long userId) {

        User user = userService.getUser(userId); // assume this fetches a User entity

        if (user != null) {
            user.setPersonalData(personalData);
            userService.editUser(user);
        } else {
            // optionally handle invalid user ID
            System.out.println("Invalid user ID: " + userId);
        }

        return "redirect:/personal-data";
    }
}
