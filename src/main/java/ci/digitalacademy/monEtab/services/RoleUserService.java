package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;


import java.util.List;
import java.util.Optional;

public interface RoleUserService {

    Optional<RoleUserDTO> findById(Long id);

    RoleUserDTO save(RoleUserDTO roleUserDTO);

    RoleUserDTO update(RoleUserDTO roleUserDTO, Long id);

    void delete(Long id);

    List<RoleUserDTO> findAll();

    List<RoleUserDTO> initRoles(List<RoleUserDTO> roles);


    List<RoleUserDTO> findByNameRole(String userRole);

    Object findByRole(String roleUser);

    RoleUserDTO partialUpdate(RoleUserDTO roleUserDTO, Long id);

    RoleUserDTO saveRoleUser(RoleUserDTO roleUserDTO);
}