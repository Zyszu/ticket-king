package pl.matzysz.controller;


import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.matzysz.domain.Address;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.User;
import pl.matzysz.repository.UserRepository;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/register-company")
public class RegisterCompanyController {

    private final CompanyService companyService;
    private final UserRepository userRepository;

    public RegisterCompanyController(
            CompanyService companyService,
            UserRepository userRepository
    ) {
        this.companyService = companyService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String index(
            Model model,
            HttpServletRequest request,
            Principal principal
    ) {
        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        if (user.getCompany() == null) {
            Company company = new Company();
            Address address = new Address();
            company.setOwner(new User());
            company.setAddress(address);
            model.addAttribute("company", company);
        } else {
            model.addAttribute("company", user.getCompany());
        }

        return  "register-company";
    }

    @PostMapping
    public String register(
            @ModelAttribute("company") Company company,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        company.setActive(false);
        company.setVerified(false);
        company.setOwner(user);
        companyService.addCompany(company);

        return "redirect:/force-logout";
    }


}
