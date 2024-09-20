package ci.digitalacademy.monEtab.services.dto;

import ci.digitalacademy.monEtab.models.School;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class UserDTO {
    private Long id_user;
    private String pseudo;
    private String password;
    private Instant createdDate;
    private String slug;
    private List<RoleUserDTO> roleUser;
    private SchoolDTO school;

}
