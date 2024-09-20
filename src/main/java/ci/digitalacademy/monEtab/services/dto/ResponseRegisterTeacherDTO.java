package ci.digitalacademy.monEtab.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterTeacherDTO {

    @JsonIgnoreProperties({"id", "phoneNumber","gender","address","user" })
    private TeacherDTO Teacher;

    @JsonIgnoreProperties({"id"})
    private AddressDTO address;

    @JsonIgnoreProperties({"id"})
    private UserDTO user;
}
