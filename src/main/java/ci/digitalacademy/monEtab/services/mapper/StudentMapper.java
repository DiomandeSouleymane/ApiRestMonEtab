package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.Student;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
import org.mapstruct.*;
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO , Student> {
}
