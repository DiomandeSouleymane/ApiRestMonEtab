package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.School;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {
}