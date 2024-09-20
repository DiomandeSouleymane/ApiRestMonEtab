package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.services.dto.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO , User> {
}
