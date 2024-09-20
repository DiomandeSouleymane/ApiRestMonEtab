package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO, Long id);

    UserDTO update(UserDTO userDTO);

    Optional<UserDTO> findById(Long id);

    List<UserDTO> findAll();

    void deleteById(Long id);

    Optional<UserDTO> findByPseudo(String username);

    List<UserDTO> initUsers(List<UserDTO> users);

    List<UserDTO> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role);

    void delete(Long id);

    UserDTO saveUser(UserDTO userDTO);

    UserDTO partialUpdate(UserDTO userDTO, Long id);

    Optional<UserDTO> findBySlug(String slug);
}
