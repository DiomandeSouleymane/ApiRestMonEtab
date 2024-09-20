package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.Absence;
import ci.digitalacademy.monEtab.services.dto.AbsenceDTO;


public final class AbsenceMapping {

    private AbsenceMapping() {
    }

    public static void partialUpdate(Absence absence, AbsenceDTO absenceDTO) {
        if (absenceDTO.getAbsenceNumber() != null) {
            absence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
        }
        if (absenceDTO.getAbsenceDate() != null) {
            absence.setAbsenceDate(absenceDTO.getAbsenceDate());
        }
    }


}
