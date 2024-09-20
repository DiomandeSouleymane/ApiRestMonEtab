package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.RoleUser;
import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;

public final class RoleUserMapping {

    private RoleUserMapping() {
    }

    public static void partialUpdate(RoleUser roleUser, RoleUserDTO roleUserDTO) {
        if (roleUser.getNameRole() != null) {
            roleUser.setNameRole(roleUserDTO.getNameRole());
        }

    }
}
