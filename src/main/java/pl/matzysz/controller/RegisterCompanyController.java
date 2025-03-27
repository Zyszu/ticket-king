package pl.matzysz.controller;


import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/register-company")
public class RegisterCompanyController {

    private final CompanyService companyService;
    private final UserRepository userRepository;
    private final UserService userService;

    public RegisterCompanyController(
            CompanyService companyService,
            UserRepository userRepository,
            UserService userService) {
        this.companyService = companyService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        Company company = new Company();
        Address address = new Address();
        company.setOwner(new User());
        company.setAddress(address);
        model.addAttribute("company", company);
        return  "register-company";
    }

    @PostMapping
    public String register(@ModelAttribute("company") Company company, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register-company"; // Updated JSP name
        }

        // Debugging output
        System.out.println(
                "Company Name: " + company.getCompanyName() +
                        " | NIP: " + company.getNip() +
                        " | Owner: " + company.getOwner().getEmail() +
                        " | Country: " + company.getAddress().getCountry() +
                        " | State: " + company.getAddress().getState() +
                        " | City: " + company.getAddress().getCity() +
                        " | ZipCode: " + company.getAddress().getZipCode() +
                        " | OwnerId: " + company.getOwner().getId()
        );


        long ownerId = company.getOwner().getId();
        company.setActive(false);
        company.setVerified(false);
        company.setOwner(userService.getUser(ownerId));
        companyService.addCompany(company); // Updated service call
        return "redirect:register-company"; // Redirect to company registration page
    }


}
