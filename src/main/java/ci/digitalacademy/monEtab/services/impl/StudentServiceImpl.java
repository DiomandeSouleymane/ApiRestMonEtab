package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.Student;
import ci.digitalacademy.monEtab.models.enumeration.Gender;
import ci.digitalacademy.monEtab.repositories.StudentRepository;
import ci.digitalacademy.monEtab.security.AuthorityConstants;
import ci.digitalacademy.monEtab.services.*;
import ci.digitalacademy.monEtab.services.dto.*;
import ci.digitalacademy.monEtab.services.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AddresseService addresseService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleUserService roleUserService;
    private final FileStorageService fileStorageService;

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.DtoToEntity(studentDTO);
        return studentMapper.ToDto(studentRepository.save(student));
    }

    @Override
    public StudentDTO uploadStudentPicture(Long id, MultipartFile picture) throws IOException {

        Optional<StudentDTO> one = findById(id);
        StudentDTO student = one.orElse(null);
        if (student!= null) {
            String upload = fileStorageService.upload(picture);
            student.setUrlPicture(upload);
            save(student);
        }
        return student;
    }


    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        Optional<Student> existingStudent = studentRepository.findById(studentDTO.getId());
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            // Mettre à jour les champs nécessaires
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setMatricule(studentDTO.getMatricule());
            student.setPhoneNumberFather(studentDTO.getPhoneNumberFather());
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            student.setBirthday(studentDTO.getBirthday());
            student.setGender(studentDTO.getGender());
            student.setClasse(studentDTO.getClasse());
            student.setUrlPicture(studentDTO.getUrlPicture());

            // Sauvegarde des modifications
            studentRepository.save(student);
            return studentMapper.ToDto(student);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @Override
    public Optional<StudentDTO> update(StudentDTO studentDTO, Long id) {

        return findOne(id).map(student -> {
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setMatricule(studentDTO.getMatricule());
            return save(student);
        });
    }


    @Override
    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id).map(student -> studentMapper.ToDto(student));
    }


    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {
            return studentMapper.ToDto(student);
        }).toList();
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByLastNameOrGenderOrMatricule(String query, String gender) {
        if (gender != null && !gender.isEmpty()) {
            return studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(query, query, Gender.valueOf(gender));
        } else {
            return studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCase(query, query);
        }
    }




    @Override
    public Optional<StudentDTO> findOne(Long id) {
        return studentRepository.findById(id).map(student -> studentMapper.ToDto(student));
    }

    public void delete(Long id) {
        log.debug("Request to delete Student by ID: {}", id);
        studentRepository.deleteById(id);  // Utilisation de la méthode deleteById
    }

    @Override
    @Transactional
    public ResponseRegisterStudentDTO registerStudent(RegistrationStudentDTO registrationStudentDTO) {
        log.debug("Request to register student {}",  registrationStudentDTO);
        AddressDTO address = modelMapper.map(registrationStudentDTO, AddressDTO.class);
        address= addresseService.save(address);

        List<RoleUserDTO> roleUsers = roleUserService.findByNameRole(AuthorityConstants.USER_ROLE);
        UserDTO user = modelMapper.map(registrationStudentDTO, UserDTO.class);
        String password = UUID.randomUUID().toString();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoleUser(roleUsers);
        user = userService.save(user);

        StudentDTO student = modelMapper.map(registrationStudentDTO, StudentDTO.class);
        student.setUser(user);
        student.setAddress(address);
        student = save(student);

        ResponseRegisterStudentDTO response = new ResponseRegisterStudentDTO();
        response.setStudent(student);
        response.setAddress(address);
        return response;


}
}