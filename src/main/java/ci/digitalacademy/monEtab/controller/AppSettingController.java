package ci.digitalacademy.monEtab.controller;


import ci.digitalacademy.monEtab.services.InitAppService;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
@Slf4j
@RequiredArgsConstructor
public class AppSettingController {

    private final InitAppService initAppService;

    @GetMapping
    public String showSettingPage(Model model){
        model.addAttribute("setting" , new AppSettingDTO());
        return "inits/setting";
    }

    @Transactional
    @PostMapping
    public String saveSettingApp(AppSettingDTO appSettingDTO) {
        initAppService.initAppSetting(appSettingDTO);
        return "redirect:/schools"; // Redirection vers le formulaire de l'école
    }

}
