package pl.matzysz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String customLogin(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";
    }

    @GetMapping(value = "/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping(value = "/force-logout")
    public String forceLogout(
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes

    ) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        SecurityContextHolder.clearContext();

        redirectAttributes.addFlashAttribute("messageInfo", model.getAttribute("messageInfo"));
        return "redirect:/home";
    }


}
