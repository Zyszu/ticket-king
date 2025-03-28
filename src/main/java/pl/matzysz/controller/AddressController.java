package pl.matzysz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Address;
import pl.matzysz.domain.User;
import pl.matzysz.service.AddressService;
import pl.matzysz.service.UserService;

@Controller
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;
    private UserService userService;

    @Autowired
    public AddressController(AddressService addressService,  UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping
    public String showAddressForm(Model model) {
        model.addAttribute("address", new Address());
        return "address";
    }

    @PostMapping
    public String saveAddress(
            @ModelAttribute("address") Address address,
            @RequestParam("userId") Long userId
    ) {

        User user = userService.getUser(userId); // assume this fetches a User entity

        if (user != null) {
            user.setAddress(address);
            userService.editUser(user);
        } else {
            // optionally handle invalid user ID
            System.out.println("Invalid user ID: " + userId);
        }

        return "redirect:/address";
    }

}
