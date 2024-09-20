package ci.digitalacademy.monEtab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class StudentCardsDTO {

    private Long idStudentCard;
    private String reference;
    private Date issueDate;
    private Date expirationDate;
    private String slug;
    private StudentDTO student;
}
