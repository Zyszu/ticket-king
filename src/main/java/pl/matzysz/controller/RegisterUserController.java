package pl.matzysz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.matzysz.domain.DTO.RecaptchaResponse;
import pl.matzysz.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.matzysz.domain.VerificationToken;
import pl.matzysz.service.EmailSenderService;
import pl.matzysz.service.UserService;
import pl.matzysz.service.VerificationTokenService;
import pl.matzysz.validator.UserValidator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register-user")
@PropertySource("classpath:application.properties")
public class RegisterUserController {

    private final UserService userService;
    private final UserValidator userValidator = new UserValidator();
    private final EmailSenderService emailSenderService;
    private final VerificationTokenService verificationTokenService;
    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @Value("${google.recaptcha.site.key}")
    String recaptchaSiteKey;

    @Value("${google.recaptcha.secret.key}")
    String recaptchaSecret;


    public RegisterUserController(
            UserService userService,
            EmailSenderService emailSenderService,
            VerificationTokenService verificationTokenService
    ) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());
        model.addAttribute("reCAPTCHASiteKey", recaptchaSiteKey);
        return "register-user";
    }

    @GetMapping("/activate/{token}")
    public String activateAccountWithToken(
            @PathVariable("token") String token,
            Model model
    ) {
        // get the token entity and check if it exists
        VerificationToken existingVerificationToken = verificationTokenService.getVerificationToken(token);
        if (existingVerificationToken == null) {
            model.addAttribute("message", "error.user.verification.token.invalid");
            return "activate-account";
        }

        // check if token is not expired
        if (!existingVerificationToken.getExpiryDate().isAfter(LocalDateTime.now())) {
            model.addAttribute("message", "error.user.verification.token.expired");
            return "activate-account";
        }

        // activate user account
        User existingUser = existingVerificationToken.getUser();
        existingUser.setActive(true);
        userService.editUser(existingUser);

        // delete no longer necessary token
        verificationTokenService.deleteVerificationToken(existingVerificationToken);

        model.addAttribute("message", "success.user.verification.token.accepted");
        return "activate-account";
    }

    @PostMapping
    public String register(
            @ModelAttribute("user") User user,
            @RequestParam("g-recaptcha-response") String recaptchaResponse,
            BindingResult bindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        // CAPTCHA
        if(!this.verify(recaptchaResponse, null)) {
            model.addAttribute("messageError", "error.captcha.verification.failed");
            model.addAttribute("reCAPTCHASiteKey", recaptchaSiteKey);
            return  "register-user";
        }

        // check if user already exists in DB
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("messageError", "error.user.email.exists");
            model.addAttribute("reCAPTCHASiteKey", recaptchaSiteKey);
            return  "register-user";
        }

        // validate email and password
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register-user";
        }

        // create a new user and deactivate account
        user.setActive(true);
//        user.setActive(false);
        userService.addUser(user);

//        // create a user account activation token
//        VerificationToken newToken =  verificationTokenService.generateVerificationToken(user);
//        String appUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
//        String tokenUrl = appUrl + "/register-user/activate/" + newToken.getToken();
//
//        // send email with the activation token
//        emailSenderService.sendEmail(
//                user.getEmail(),
//                "Welcome on board!",
//                "Hi, thank you for registering in Ticket King!\n\n" +
//                        "Please activate your account by clicking the link below:\n" +
//                        tokenUrl
//        );

        redirectAttributes.addFlashAttribute("messageInfo", "success.user.do.activate.account");
        return "redirect:/login";
    }

    private boolean verify(String responseToken, String remoteIp) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("secret", recaptchaSecret);
        body.add("response", responseToken);
        if (remoteIp != null) {
            body.add("remoteip", remoteIp);
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<RecaptchaResponse> response = restTemplate.exchange(
                VERIFY_URL,
                HttpMethod.POST,
                requestEntity,
                RecaptchaResponse.class
        );

        RecaptchaResponse recaptcha = response.getBody();
//        System.out.println("Response from Google: " + recaptcha); // debug
        return recaptcha != null && recaptcha.isSuccess();
    }

}
