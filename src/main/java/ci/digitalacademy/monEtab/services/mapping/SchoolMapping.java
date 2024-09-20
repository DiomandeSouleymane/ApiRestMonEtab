package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.School;
import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;

public final class SchoolMapping {

    private SchoolMapping() {
    }

    public static void partialUpdate(School school, SchoolDTO schoolDTO) {
        if (schoolDTO.getNameSchool() != null) {
            school.setNameSchool(schoolDTO.getNameSchool());
        }
        if (schoolDTO.getUrlLogo() != null) {
            school.setUrlLogo(schoolDTO.getUrlLogo());
        }
    }
}
