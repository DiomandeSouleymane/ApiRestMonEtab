package ci.digitalacademy.monEtab.services.dto;

import ci.digitalacademy.monEtab.models.Absence;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
public class StudentDTO extends PersonDTO {
    private String matricule;
    private String phoneNumberFather;
    private String classe;
    private String slug;
    private Set<AbsenceDTO> absences;
}
