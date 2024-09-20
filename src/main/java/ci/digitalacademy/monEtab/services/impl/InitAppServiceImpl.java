package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.AppSetting;
import ci.digitalacademy.monEtab.services.*;
import ci.digitalacademy.monEtab.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
@Service
public class InitAppServiceImpl implements InitAppService {

    private AppSettingService appSettingService;

    private SchoolService schoolService;
    private RoleUserService roleUserService;
    private UserService userService;


    @Override
    public AppSettingDTO initAppSetting(AppSettingDTO appSettingDTO) {
        return appSettingService.initAppSetting(appSettingDTO);
    }

    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        // Vérifiez si l'école existe déjà
        SchoolDTO existingSchool = schoolService.existingSchool();

        if (existingSchool == null) {
            // Si l'école n'existe pas, enregistrez d'abord les paramètres AppSetting
            AppSettingDTO appSetting = appSettingService.save(appSettingDTO);

            // Puis associez les paramètres AppSetting à l'école
            schoolDTO.setAppSetting(appSettingDTO);
            return schoolService.save(schoolDTO);
        }

        return existingSchool;
    }

    @Override
    public List<RoleUserDTO> initRoleUsers(List<RoleUserDTO> roleUserDTOList) {
        return roleUserService.initRoles(roleUserDTOList);
    }

    public boolean isAppConfigured() {
        // Logique pour vérifier si les paramètres de l'application sont définis
        return false;
    }

    public boolean isSchoolConfigured() {
        // Logique pour vérifier si les paramètres de l'école sont définis
        return false;
    }




}