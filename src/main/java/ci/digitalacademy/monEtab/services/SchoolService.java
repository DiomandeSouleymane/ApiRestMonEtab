package ci.digitalacademy.monEtab.services;


import ci.digitalacademy.monEtab.services.dto.SchoolDTO;

import java.util.List;
import java.util.Optional;

public interface SchoolService {


    SchoolDTO save(SchoolDTO schoolDTO);

    List<SchoolDTO> findAll();

    SchoolDTO findOne(Long id);

    void delete(Long id);

    Optional<SchoolDTO> update(SchoolDTO schoolDTO);

    SchoolDTO existingSchool();

    SchoolDTO initSchool(SchoolDTO schoolDTO);

    Optional<SchoolDTO> update(SchoolDTO schoolDTO, Long id);

    Object findById(Long id);

    SchoolDTO partialUpdate(SchoolDTO schoolDTO, Long id);

    SchoolDTO saveSchool(SchoolDTO schoolDTO);
}
