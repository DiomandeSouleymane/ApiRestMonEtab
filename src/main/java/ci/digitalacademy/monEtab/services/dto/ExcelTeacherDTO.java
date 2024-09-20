package ci.digitalacademy.monEtab.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ExcelTeacherDTO {

    private Boolean available;

    private String specialty;

    private String firstName;

    private String lastName;

    private String numbers;

    private LocalDate birthday;
}
