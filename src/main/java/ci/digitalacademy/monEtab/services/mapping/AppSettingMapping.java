package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.AppSetting;
import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;

public final class AppSettingMapping {

    private AppSettingMapping() {

    }

    public static void partialUpdate(AppSetting appSetting, AppSettingDTO appSettingDTO) {

        if (appSettingDTO.getSmtpPassword() != null) {
            appSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
        }
        if (appSettingDTO.getSmtpPort() != null) {
            appSetting.setSmtpPort(appSettingDTO.getSmtpPort());
        }
        if (appSettingDTO.getSmtpServer() != null) {
            appSetting.setSmtpServer(appSettingDTO.getSmtpServer());
        }
        if (appSettingDTO.getSmtpUsername() != null) {
            appSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
        }

    }
}
