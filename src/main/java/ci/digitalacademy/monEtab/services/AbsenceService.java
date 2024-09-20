package ci.digitalacademy.monEtab.services;


import ci.digitalacademy.monEtab.services.dto.AbsenceDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


public interface AbsenceService {


    AbsenceDTO save(AbsenceDTO absenceDTO);

    AbsenceDTO update(AbsenceDTO absenceDTO);
    AbsenceDTO update(AbsenceDTO absenceDTO, Long id);

    Optional<AbsenceDTO> findById(Long id);

    Optional<AbsenceDTO> findOne(Long id);
    List<AbsenceDTO> findAll();
    void delete(Long id);
    AbsenceDTO saveAbsence(AbsenceDTO absenceDTO);

    AbsenceDTO partialUpdate(AbsenceDTO absenceDTO, Long id);
}
