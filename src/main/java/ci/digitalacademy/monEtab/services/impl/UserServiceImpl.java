package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.repositories.UserRepository;
import ci.digitalacademy.monEtab.services.UserService;
import ci.digitalacademy.monEtab.services.dto.UserDTO;
import ci.digitalacademy.monEtab.services.mapper.UserMapper;
import ci.digitalacademy.monEtab.services.mapping.UserMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Saving user {}", userDTO);
        User user  = userMapper.DtoToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.ToDto(savedUser);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        return findById(userDTO.getId_user()).map(existingUser ->{
            existingUser.setPseudo(userDTO.getPseudo());
            existingUser.setPassword(userDTO.getPassword());
            return save(existingUser);
        }).orElseThrow(()->new RuntimeException(""));
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {

        userDTO.setId_user(id);
        return update(userDTO);
    }



    @Override
    public Optional<UserDTO> findById(Long id) {
        log.debug("Finding user by id {}", id);
        return userRepository.findById(id)
                .map(userMapper::ToDto);
    }

    @Override
    public List<UserDTO> findAll() {
        log.debug("Finding all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::ToDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting user by id {}", id);
        userRepository.deleteById(id);
    }
    @Override
    public void delete(Long id) {

        log.debug("Request to delete user: {}", id);
        userRepository.deleteById(id);

    }

    @Override
    public Optional<UserDTO> findByPseudo(String pseudo) {

        return userRepository.findByPseudo(pseudo).map(user -> userMapper.ToDto(user));
    }

    @Override
    public List<UserDTO> initUsers(List<UserDTO> users) {

        List<UserDTO> userDTOS = findAll();
        if (userDTOS.isEmpty()){
            users.forEach(user -> save(user));
        }

        return findAll();
    }

    @Override
    public List<UserDTO> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role) {
        List<User> users = userRepository.findByCreatedDateLessThanAndRoleUserNameRole(createdDate, role);
        return users.stream().map(user -> userMapper.ToDto(user)).toList();
    }



    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        final String slug = SlugifyUtils.generate(userDTO.getPseudo().toString());
        userDTO.setSlug(slug);
        return save(userDTO);
    }

    @Override
    public UserDTO partialUpdate(UserDTO userDTO, Long id) {
        return userRepository.findById(id).map(user -> {
           UserMapping.partialUpdate(user, userDTO);
            return user;
        }).map(userRepository::save).map(userMapper::ToDto).orElse(null);
    }
    @Override
    public Optional<UserDTO> findBySlug(String slug) {
        // Correctement mappe un User vers UserDTO via le mapper
        return userRepository.findBySlug(slug)
                .map(userMapper::ToDto);
    }



}
