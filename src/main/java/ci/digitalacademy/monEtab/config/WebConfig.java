package ci.digitalacademy.monEtab.config;

import ci.digitalacademy.monEtab.services.impl.AppSettingInterceptorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AppSettingInterceptorImpl appSettingInterceptor;

   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appSettingInterceptor)
                .addPathPatterns("/**") // Appliquer à toutes les pages
                .excludePathPatterns("/login", "/settings", "/schools","/css/**", "/js/**", "/images/**","/fontawesome/**","/icons/**"); // Exclure les pages de config et statiques
    }*/
}
