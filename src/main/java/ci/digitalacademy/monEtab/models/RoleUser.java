package ci.digitalacademy.monEtab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "role_user")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_user")
    private Long idRoleUser;
    @Column(unique = true , name = "name_role")
    private String nameRole;
    private String slug;

}