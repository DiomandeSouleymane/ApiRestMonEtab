package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.models.Student;
import ci.digitalacademy.monEtab.services.dto.RegistrationStudentDTO;
import ci.digitalacademy.monEtab.services.dto.ResponseRegisterStudentDTO;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);

    StudentDTO uploadStudentPicture(Long id, MultipartFile picture) throws IOException;

    StudentDTO update(StudentDTO studentDTO);

    Optional<StudentDTO> update(StudentDTO studentDTO, Long id);

    Optional<StudentDTO> findById(Long id);

    List<StudentDTO> findAll();

    void deleteById(Long id);

    List<Student> findByLastNameOrGenderOrMatricule(String query , String gender);

    Optional<StudentDTO> findOne(Long id);

    void delete(Long id);

    ResponseRegisterStudentDTO registerStudent(RegistrationStudentDTO registrationStudentDTO);
}
