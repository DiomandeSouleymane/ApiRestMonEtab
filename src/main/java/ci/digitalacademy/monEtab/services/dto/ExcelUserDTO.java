package ci.digitalacademy.monEtab.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExcelUserDTO {

    private String pseudo;

    private Instant createdDate;

}
