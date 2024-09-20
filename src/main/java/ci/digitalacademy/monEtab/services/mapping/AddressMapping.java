package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;


public final class AddressMapping {

    private AddressMapping() {
    }

    public static void partialUpdate(Address address, AddressDTO addressDTO) {
        if (addressDTO.getCountry() != null) {
            address.setCountry(addressDTO.getCountry());
        }
        if (addressDTO.getCity() != null) {
            address.setCity(addressDTO.getCity());
        }
        if (addressDTO.getStreet() != null) {
            address.setStreet(addressDTO.getStreet());
        }
    }

}
