package ci.digitalacademy.monEtab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id_address;
    private String city;
    private String street;
    private String country;
    private String slug;
}
