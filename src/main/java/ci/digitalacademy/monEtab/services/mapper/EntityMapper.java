package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;

public interface EntityMapper <D , E>{

    D ToDto(E entity);
    E DtoToEntity(D dto);

}

