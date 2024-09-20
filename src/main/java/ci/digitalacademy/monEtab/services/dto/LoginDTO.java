package ci.digitalacademy.monEtab.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String username;
    private String password;
    private Boolean rememberMe;
}
