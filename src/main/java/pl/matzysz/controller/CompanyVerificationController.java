package pl.matzysz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.CompanyVerification;
import pl.matzysz.domain.User;
import pl.matzysz.service.CompanyService;
import pl.matzysz.service.CompanyVerificationService;
import pl.matzysz.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/verifications")
public class CompanyVerificationController {

    private final CompanyVerificationService companyVerificationService;
    private final CompanyService companyService;
    private final UserService userService;

    @Autowired
    public CompanyVerificationController(CompanyVerificationService companyVerificationService, CompanyService companyService, UserService userService) {
        this.companyVerificationService = companyVerificationService;
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping(value = "/your-verifications")
    public String listCompanyVerifications(
            Model model,
            Principal principal
    ) {
        User owner = userService.getUserByEmail(principal.getName());
        Company company = companyService.getCompanyByOwner(owner);

        model.addAttribute("listCompanyVerification", company.getVerificationList());
        return "your-verifications";
    }

    @GetMapping(value = "/verification-panel")
    public String someMethod(
            Model model,
            Principal principal
    ) {
        List<CompanyVerification> listCompanyVerification = companyVerificationService.listNotCompleteCompanyVerification();

        model.addAttribute("listCompanyVerification", listCompanyVerification);
        return "verification-panel";
    }

    @GetMapping(value = "/verification-panel/case/{id}")
    public String viewVerificationCase(@PathVariable("id") Long id, Model model) {
        CompanyVerification verification = companyVerificationService.findCompanyVerificationById(id);
        if (verification == null) return "redirect:/verifications/verification-panel";

        Company company = verification.getCompany();
        User owner = company.getOwner();

        model.addAttribute("verification", verification);
        model.addAttribute("company", company);
        model.addAttribute("owner", owner);

        return "verification-case";
    }

    @PostMapping(value = "/verification-panel/case/{id}")
    public String handleVerificationAction(
            @PathVariable("id") Long id,
            @RequestParam("accepted") boolean accepted,
            @RequestParam("comment") String comment,
            Principal principal
    ) {
        CompanyVerification verification = companyVerificationService.findCompanyVerificationById(id);
        if (verification == null || verification.isVerificationComplete()) {
            return "redirect:/verifications/verification-panel";
        }

        User reviewer = userService.getUserByEmail(principal.getName());

        verification.setComment(comment);
        verification.setReviewedBy(reviewer);

        if (accepted) {
            companyVerificationService.acceptCompanyVerification(verification);
        } else {
            companyVerificationService.rejectCompanyVerification(verification);
        }

        return "redirect:/verifications/verification-panel";
    }

}
