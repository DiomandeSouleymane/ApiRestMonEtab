package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.*;

import java.util.List;

public interface InitAppService {


    AppSettingDTO initAppSetting(AppSettingDTO appSettingDTO);

    SchoolDTO initSchool(SchoolDTO schoolDTO , AppSettingDTO appSettingDTO);

    List<RoleUserDTO> initRoleUsers(List<RoleUserDTO> roleUserDTOList);


    boolean isAppConfigured();

    boolean isSchoolConfigured();
}
