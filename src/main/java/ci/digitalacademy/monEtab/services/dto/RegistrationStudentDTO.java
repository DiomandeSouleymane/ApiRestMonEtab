package ci.digitalacademy.monEtab.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationStudentDTO {

    private String firstName;
    private String lastName;
    private String matricule;
    private String country;
    private String city;
    private String street;
    private String pseudo;
    private String email;

}
