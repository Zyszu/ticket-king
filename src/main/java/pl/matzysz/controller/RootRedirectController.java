package pl.matzysz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootRedirectController {

    @GetMapping(value = "/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}
