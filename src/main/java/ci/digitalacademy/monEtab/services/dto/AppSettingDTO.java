package ci.digitalacademy.monEtab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSettingDTO {
    private Long id;
    private String smtpServer;
    private Integer smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private String slug;
}
