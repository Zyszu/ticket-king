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
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.matzysz.domain.Address;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.User;
import pl.matzysz.repository.CompanyRepository;
import pl.matzysz.repository.UserRepository;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.CompanyVerificationService;
import pl.matzysz.service.UserService;
import pl.matzysz.validator.AddressValidator;
import pl.matzysz.validator.CompanyValidator;

import java.security.Principal;

@Controller
@RequestMapping("/register-company")
public class RegisterCompanyController {

    private final CompanyService companyService;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyVerificationService companyVerificationService;
    private final CompanyValidator companyValidator = new CompanyValidator();
    private final AddressValidator addressValidator = new AddressValidator();

    public RegisterCompanyController(
            CompanyService companyService,
            UserRepository userRepository,
            CompanyRepository companyRepository, CompanyVerificationService companyVerificationService) {
        this.companyService = companyService;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.companyVerificationService = companyVerificationService;
    }

    @GetMapping
    public String index(
            Model model,
            HttpServletRequest request,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        if (user.getPersonalData() == null) {
            redirectAttributes.addFlashAttribute("messageError", "error.register.controller.no.personal.data");
            return "redirect:/home";
        }

        if (user.getAddress() == null) {
            redirectAttributes.addFlashAttribute("messageError", "error.register.controller.no.user.address");
            return "redirect:/home";
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
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/home"; // + errors
        }

        companyValidator.validate(company, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register-company";
        }


        company.setActive(false);
        company.setVerified(false);
        company.setOwner(user);

        companyVerificationService.startCompanyVerification(company);
        redirectAttributes.addFlashAttribute("messageInfo", "info.register.controller.success");
        return "redirect:/force-logout";
    }


}
