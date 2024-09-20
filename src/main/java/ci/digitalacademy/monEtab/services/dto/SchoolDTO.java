package ci.digitalacademy.monEtab.services.dto;

import ci.digitalacademy.monEtab.models.AppSetting;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SchoolDTO {
    private Long id_school;
    private String nameSchool;
    private String urlLogo;
    private String slug;
    private AppSettingDTO appSetting;
}
