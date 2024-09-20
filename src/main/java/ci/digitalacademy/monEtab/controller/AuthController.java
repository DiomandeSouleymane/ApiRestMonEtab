package ci.digitalacademy.monEtab.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home"; // Redirection si l'utilisateur est connecté
        }
        return "auth/login"; // Page de connexion si l'utilisateur n'est pas connecté
    }

}