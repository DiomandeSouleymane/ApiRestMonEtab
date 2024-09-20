package ci.digitalacademy.monEtab.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ExcelStudentDTO {

    private String matricule;

    private String firstName;

    private String lastName;

    private String numbers;

    private LocalDate birthday;

}
