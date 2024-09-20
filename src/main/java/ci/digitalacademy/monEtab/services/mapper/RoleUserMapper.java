package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.RoleUser;
import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface RoleUserMapper extends EntityMapper<RoleUserDTO  , RoleUser> {
}