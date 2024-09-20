package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.StudentCards;
import ci.digitalacademy.monEtab.services.dto.StudentCardsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentCardsMapper extends EntityMapper<StudentCardsDTO, StudentCards>{

}
