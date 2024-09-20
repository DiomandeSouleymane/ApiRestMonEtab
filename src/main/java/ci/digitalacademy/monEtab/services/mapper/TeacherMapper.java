package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {

}