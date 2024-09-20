package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.services.AppSettingService;
import ci.digitalacademy.monEtab.services.InitAppService;
import ci.digitalacademy.monEtab.services.SchoolService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppSettingInterceptorImpl implements HandlerInterceptor {

    private final InitAppService initAppService;
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Vérifier si les paramètres de l'application sont configurés
        List<?> appSettings = appSettingService.findAll();
        if (appSettings.isEmpty()) {
            response.sendRedirect("/settings");
            return false; // Empêche la poursuite de la requête
        }

        // Vérifier si une école est configurée
        List<?> schools = schoolService.findAll();
        if (schools.isEmpty()) {
            response.sendRedirect("/schools");
            return false; // Empêche la poursuite de la requête
        }

        return true; // Tout est configuré, autoriser l'accès à la page
    }
}

