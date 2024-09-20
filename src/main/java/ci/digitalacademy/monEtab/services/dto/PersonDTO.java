package ci.digitalacademy.monEtab.services.dto;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.models.enumeration.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class PersonDTO {


    private Long id;
    private LocalDate birthday;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String urlPicture;
    private String slug;
    private Gender gender;
    private AddressDTO address;
    private UserDTO user;
}
