package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address>{

}
