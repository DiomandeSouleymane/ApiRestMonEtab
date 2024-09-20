package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.StudentCards;
import ci.digitalacademy.monEtab.repositories.StudentCardsRepository;
import ci.digitalacademy.monEtab.services.StudentCardsService;
import ci.digitalacademy.monEtab.services.dto.StudentCardsDTO;
import ci.digitalacademy.monEtab.services.mapper.StudentCardsMapper;
import ci.digitalacademy.monEtab.services.mapping.StudentCardsMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class StudentCardsServiceImpl implements StudentCardsService {

    private final StudentCardsRepository studentCardsRepository;
    private final StudentCardsMapper studentCardsMapper;

    @Override
    public StudentCardsDTO save(StudentCardsDTO studentCardsDTO) {
        log.debug("Saving StudentCards {}", studentCardsDTO);
        StudentCards studentCards = studentCardsMapper.DtoToEntity(studentCardsDTO);
        return studentCardsMapper.ToDto(studentCardsRepository.save(studentCards));
    }

    @Override
    public Optional<StudentCardsDTO> update(StudentCardsDTO studentCardsDTO) {
        log.debug("Request to update StudentCards : {}", studentCardsDTO);
        return Optional.ofNullable(findById(studentCardsDTO.getIdStudentCard()).map(existingStudentCards -> {
            StudentCards studentCards = studentCardsMapper.DtoToEntity(studentCardsDTO);
            studentCards.setIdStudentCard(existingStudentCards.getIdStudentCard());  // Ensure the existing teacher's ID is preserved
            return save(studentCardsDTO);
        }).orElseThrow(() -> new RuntimeException("Teacher not found")));
    }


    @Override
    public Optional<StudentCardsDTO> update(StudentCardsDTO studentCardsDTO, Long id) {
        log.debug("Request to update StudentCards with ID : {}", id);
        studentCardsDTO.setIdStudentCard(id);  // Set the correct ID for the update
        return update(studentCardsDTO);
    }

    @Override
    public Optional<StudentCardsDTO> findById(Long id) {
        log.debug("Request to find StudentCards by ID : {}", id);
        return studentCardsRepository.findById((id))
                .map(studentCardsMapper::ToDto);
    }


    @Override
    public Optional<StudentCardsDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<StudentCardsDTO> findAll() {
        log.debug("Request to find all StudentCards");
        return studentCardsRepository.findAll()
                .stream()
                .map(studentCardsMapper::ToDto)
                .toList();
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentCards by ID: {}", id);
        studentCardsRepository.deleteById((id));  // Utilisation de la méthode deleteById
    }


    @Override
    public StudentCardsDTO partialUpdate(StudentCardsDTO studentCardsDTO, Long id) {
        return studentCardsRepository.findById((id)).map(studentCards -> {
            StudentCardsMapping.partialUpdate(studentCards, studentCardsDTO);
            return studentCards;
        }).map(studentCardsRepository::save).map(studentCardsMapper::ToDto).orElse(null);
    }

    @Override
    public StudentCardsDTO saveStudentCards(StudentCardsDTO studentCardsDTO) {
        final String slug = SlugifyUtils.generate(String.valueOf(studentCardsDTO.getIssueDate()));
        studentCardsDTO.setSlug(slug);
        return save(studentCardsDTO);
    }

}
