package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.repositories.TeacherRepository;
import ci.digitalacademy.monEtab.security.AuthorityConstants;
import ci.digitalacademy.monEtab.services.AddresseService;
import ci.digitalacademy.monEtab.services.RoleUserService;
import ci.digitalacademy.monEtab.services.TeacherService;
import ci.digitalacademy.monEtab.services.UserService;
import ci.digitalacademy.monEtab.services.dto.*;
import ci.digitalacademy.monEtab.services.mapper.TeacherMapper;
import ci.digitalacademy.monEtab.services.mapping.TeacherMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private final AddresseService addresseService;
     private final TeacherMapper teacherMapper;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleUserService roleUserService;

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        log.debug("Request to save Teacher : {}", teacherDTO);
        Teacher teacher = teacherMapper.DtoToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.ToDto(savedTeacher);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        log.debug("Request to update Teacher : {}", teacherDTO);
        return findById(teacherDTO.getId()).map(existingTeacher -> {
            Teacher teacher = teacherMapper.DtoToEntity(teacherDTO);
            teacher.setId(existingTeacher.getId());  // Ensure the existing teacher's ID is preserved
            return save(teacherDTO);
        }).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO, Long id) {
        log.debug("Request to update Teacher with ID : {}", id);
        teacherDTO.setId(id);  // Set the correct ID for the update
        return update(teacherDTO);
    }

    @Override
    public Optional<TeacherDTO> findById(Long id) {
        log.debug("Request to find Teacher by ID : {}", id);
        return teacherRepository.findById(id)
                .map(teacherMapper::ToDto);
    }

    @Override
    public List<TeacherDTO> findAll() {
        log.debug("Request to find all Teachers");
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::ToDto)
                .toList();
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teacher by ID: {}", id);
        teacherRepository.deleteById(id);  // Utilisation de la méthode deleteById
    }


    @Override
    public List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query, String gender) {
        log.debug("Request to find Teachers by lastName, specialty and gender");
        List<Teacher> teachers = teacherRepository.findByLastNameOrSpecialtyAndGender(query, query, Gender.valueOf(gender));
        return teachers.stream()
                .map(teacherMapper::ToDto)
                .toList();
    }

    @Override
    public Optional<TeacherDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public TeacherDTO partialUpdate(TeacherDTO teacherDTO, Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            TeacherMapping.partialUpdate(teacher, teacherDTO);
            return teacher;
        }).map(teacherRepository::save).map(teacherMapper::ToDto).orElse(null);
    }





    @Override
    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
        final String slug = SlugifyUtils.generate(teacherDTO.getPhoneNumber());
        teacherDTO.setSlug(slug);
        return save(teacherDTO);
    }



    @Override
    public ResponseRegisterTeacherDTO registerTeacher(RegistrationTeacherDTO registrationTeacherDTO) {

        log.debug("Request to register student {}", registrationTeacherDTO);
        AddressDTO address = modelMapper.map(registrationTeacherDTO, AddressDTO.class);
        address = addresseService.save(address);

        List<RoleUserDTO> roleUsers = roleUserService.findByNameRole(AuthorityConstants.USER_ROLE);
        UserDTO user = modelMapper.map(registrationTeacherDTO, UserDTO.class);
        String password = UUID.randomUUID().toString();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoleUser(roleUsers);
        user = userService.save(user);

        TeacherDTO teacher = modelMapper.map(registrationTeacherDTO, TeacherDTO.class);
        teacher.setUser(user);
        teacher.setAddress(address);
        teacher = save(teacher);

        ResponseRegisterTeacherDTO response = new ResponseRegisterTeacherDTO();
        response.setTeacher(teacher);
        response.setAddress(address);
        return response;


    }


}


