package ci.digitalacademy.monEtab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RoleUserDTO {
    private Long idRoleUser;
    private String nameRole;
    private String slug;
}
