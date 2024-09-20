package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.services.dto.UserDTO;

public final class UserMapping {


    private UserMapping() {
    }

    public static void partialUpdate(User user, UserDTO userDTO) {
        if (userDTO.getPseudo() != null) {
            user.setPseudo(userDTO.getPseudo());
        }
    }
}
