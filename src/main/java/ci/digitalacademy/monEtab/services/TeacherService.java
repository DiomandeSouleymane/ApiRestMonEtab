package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.*;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    TeacherDTO save(TeacherDTO teacherDTO);
    TeacherDTO update(TeacherDTO teacherDTO);
    TeacherDTO update(TeacherDTO teacherDTO, Long id);
    Optional<TeacherDTO> findById(Long id);
    List<TeacherDTO> findAll();

    void delete(Long id);

    List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query, String gender);

    Optional<TeacherDTO> findOne(Long id);

    TeacherDTO partialUpdate(TeacherDTO teacherDTO, Long id);;
    TeacherDTO saveTeacher(TeacherDTO teacherDTO);

    ResponseRegisterTeacherDTO registerTeacher(RegistrationTeacherDTO registrationTeacherDTO);

}
