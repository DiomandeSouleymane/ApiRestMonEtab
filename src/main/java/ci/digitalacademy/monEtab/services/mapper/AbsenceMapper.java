package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.Absence;
import ci.digitalacademy.monEtab.services.dto.AbsenceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbsenceMapper extends EntityMapper<AbsenceDTO , Absence> {
}

