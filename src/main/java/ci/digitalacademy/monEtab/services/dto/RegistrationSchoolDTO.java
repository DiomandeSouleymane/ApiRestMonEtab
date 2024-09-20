package ci.digitalacademy.monEtab.services.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Data
public class RegistrationSchoolDTO extends SchoolDTO {

    private MultipartFile file;
}
