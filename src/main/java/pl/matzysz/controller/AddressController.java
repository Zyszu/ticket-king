package pl.matzysz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.matzysz.domain.Address;
import pl.matzysz.domain.User;
import pl.matzysz.service.AddressService;
import pl.matzysz.service.UserService;
import pl.matzysz.validator.AddressValidator;

import java.security.Principal;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;
    private final AddressValidator addressValidator = new AddressValidator();

    @Autowired
    public AddressController(
            AddressService addressService,
            UserService userService
    ) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping
    public String showAddressForm(
            Model model,
            Principal principal
    ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        if (user.getAddress() != null) {
            model.addAttribute("address", user.getAddress());
        } else {
            model.addAttribute("address", new Address());
        }

        return "address";
    }

    @PostMapping
    public String saveAddress(
            @ModelAttribute("address") Address address,
            BindingResult  bindingResult,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        addressValidator.validate(address, bindingResult);

        if (bindingResult.hasErrors()) {
            return "address";
        }

        user.setAddress(address);
        userService.editUser(user);

        redirectAttributes.addFlashAttribute("messageInfo", "info.address.controller.success");
        return "redirect:/home";
    }

}
