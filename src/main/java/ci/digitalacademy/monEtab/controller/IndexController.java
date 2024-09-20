package ci.digitalacademy.monEtab.controller;

import ci.digitalacademy.monEtab.services.AppSettingService;
import ci.digitalacademy.monEtab.services.SchoolService;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class IndexController {

  /*  private final SchoolService schoolService;
    private final AppSettingService appSettingService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Premièrement rediriger vers le login
        return "redirect:/login";
    }

    @GetMapping("/post-login")
    public String postLogin(Model model) {
        // Après le login, vérifier si les settings sont configurés
        List<AppSettingDTO> appSetting = appSettingService.findAll();

        if (appSetting.isEmpty()) {
            return "redirect:/settings";
        } else {
            return "redirect:/post-settings";
        }
    }

    @GetMapping("/post-settings")
    public String postSettings(Model model) {
        // Après les settings, vérifier s'il existe des écoles
        List<SchoolDTO> schools = schoolService.findAll();

        if (schools.isEmpty()) {
            return "redirect:/schools";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home() {
        // Finalement, rediriger vers la page d'accueil
        return "home";
    }*/
}


