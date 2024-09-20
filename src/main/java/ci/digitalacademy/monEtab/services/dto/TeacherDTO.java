package ci.digitalacademy.monEtab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TeacherDTO extends PersonDTO{

    private boolean available;
    private String specialty;
}