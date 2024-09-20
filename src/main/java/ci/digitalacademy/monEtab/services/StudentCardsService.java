package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.StudentCardsDTO;

import java.util.List;
import java.util.Optional;


public interface StudentCardsService {

    StudentCardsDTO save(StudentCardsDTO studentCardsDTO);

    Optional<StudentCardsDTO> update(StudentCardsDTO studentCardsDTO);

    Optional<StudentCardsDTO> update(StudentCardsDTO studentCardsDTO, Long id);

    Optional<StudentCardsDTO> findById(Long id);

    Optional<StudentCardsDTO> findOne(Long id);
    List<StudentCardsDTO> findAll();
    void delete(Long id);

    StudentCardsDTO partialUpdate(StudentCardsDTO studentCardsDTO, Long id);

    StudentCardsDTO saveStudentCards(StudentCardsDTO studentCardsDTO);
}
