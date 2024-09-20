package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddresseService {


    AddressDTO save(AddressDTO addressDTO);

    Optional<AddressDTO> update(AddressDTO addressDTO);

    Optional<AddressDTO> update(AddressDTO addressDTO, Long id);

    Optional<AddressDTO> findById(Long id);

    Optional<AddressDTO> findOne(Long id);
    List<AddressDTO> findAll();
    void delete(Long id);

    AddressDTO partialUpdate(AddressDTO addressDTO, Long id);

    AddressDTO saveAddress(AddressDTO addressDTO);
}
