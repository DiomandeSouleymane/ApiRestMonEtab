package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.repositories.AddressRepository;
import ci.digitalacademy.monEtab.services.AddresseService;
import ci.digitalacademy.monEtab.services.RoleUserService;
import ci.digitalacademy.monEtab.services.StudentService;
import ci.digitalacademy.monEtab.services.UserService;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.mapper.AddressMapper;
import ci.digitalacademy.monEtab.services.mapper.TeacherMapper;
import ci.digitalacademy.monEtab.services.mapping.AddressMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddresseService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Saving address {}", addressDTO);
        Address address = addressMapper.DtoToEntity(addressDTO);
        return addressMapper.ToDto(addressRepository.save(address));
    }

    @Override
    public Optional<AddressDTO> update(AddressDTO addressDTO) {
        log.debug("Request to update Address : {}", addressDTO);
        return Optional.ofNullable(findById(addressDTO.getId_address()).map(existingAddress -> {
            Address address = addressMapper.DtoToEntity(addressDTO);
            address.setId_address(existingAddress.getId_address());  // Ensure the existing teacher's ID is preserved
            return save(addressDTO);
        }).orElseThrow(() -> new RuntimeException("Teacher not found")));
    }


    @Override
    public Optional<AddressDTO> update(AddressDTO addressDTO, Long id) {
        log.debug("Request to update Address with ID : {}", id);
        addressDTO.setId_address(id);  // Set the correct ID for the update
        return update(addressDTO);
    }

    @Override
    public Optional<AddressDTO> findById(Long id) {
        log.debug("Request to find Address by ID : {}", id);
        return addressRepository.findById((id))
                .map(addressMapper::ToDto);
    }

    @Override
    public List<AddressDTO> findAll() {
        log.debug("Request to find all Address");
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::ToDto)
                .toList();
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address by ID: {}", id);
        addressRepository.deleteById((id));  // Utilisation de la méthode deleteById
    }

    @Override
    public Optional<AddressDTO> findOne(Long id) {
        return Optional.empty();
    }



    @Override
    public AddressDTO partialUpdate(AddressDTO addressDTO, Long id) {
        return addressRepository.findById((id)).map(address -> {
            AddressMapping.partialUpdate(address, addressDTO);
            return address;
        }).map(addressRepository::save).map(addressMapper::ToDto).orElse(null);
    }


    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        final String slug = SlugifyUtils.generate(addressDTO.getCountry());
        addressDTO.setSlug(slug);
        return save(addressDTO);
    }

}
