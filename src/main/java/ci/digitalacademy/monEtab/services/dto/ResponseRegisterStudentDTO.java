package ci.digitalacademy.monEtab.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterStudentDTO {

    @JsonIgnoreProperties({"id", "phoneNumberFather", "classe","absences","gender","address","user" })
    private StudentDTO student;

    @JsonIgnoreProperties({"id"})
    private AddressDTO address;

    @JsonIgnoreProperties({"id"})
   private UserDTO user;


}
