package ci.digitalacademy.monEtab.services.impl;


import ci.digitalacademy.monEtab.models.RoleUser;
import ci.digitalacademy.monEtab.repositories.RoleUserRepository;
import ci.digitalacademy.monEtab.services.RoleUserService;
import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;
import ci.digitalacademy.monEtab.services.mapper.RoleUserMapper;
import ci.digitalacademy.monEtab.services.mapping.RoleUserMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
@Slf4j
public class RoleUserServiceImpl implements RoleUserService {



    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;



    @Override
    public RoleUserDTO save(RoleUserDTO roleUserDTO) {
        log.debug("Saving role {}", roleUserDTO);
        RoleUser roleUser = roleUserMapper.DtoToEntity(roleUserDTO);
        return roleUserMapper.ToDto(roleUserRepository.save(roleUser));
    }

    @Override
    public RoleUserDTO update(RoleUserDTO roleUserDTO, Long id) {
        log.debug("Request to update RoleUser : {}", roleUserDTO);
        return findById(roleUserDTO.getIdRoleUser()).map(existingRoleUser-> {
            RoleUser roleUser = roleUserMapper.DtoToEntity(roleUserDTO);
            roleUser.setIdRoleUser(existingRoleUser.getIdRoleUser());  // Ensure the existing teacher's ID is preserved
            return save(roleUserDTO);
        }).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @Override
    public Optional<RoleUserDTO> findById(Long id) {
        return roleUserRepository.findById(id)
                .map(roleUserMapper::ToDto);
    }


    @Override
    public List<RoleUserDTO> findAll() {
        return  roleUserRepository.findAll()
                .stream()
                .map(roleUser ->
                        roleUserMapper.ToDto(roleUser)).toList();
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roles) {
        log.debug("Request to init roles {}", roles);
        List<RoleUserDTO> listRoles = findAll();

        if (listRoles.isEmpty()) {
            roles.forEach(roleUserDTO -> save(roleUserDTO));
        }
        return findAll();
    }

    @Override
    public List<RoleUserDTO> findByNameRole(String userRole) {
        return null;
    }

    @Override
    public Object findByRole(String roleUser) {
        return roleUserRepository.findByNameRole(roleUser).stream().map( role->{
            return roleUserMapper.ToDto(role);
        }).collect(Collectors.toSet());

    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teacher by ID: {}", id);
        roleUserRepository.deleteById(id);  // Utilisation de la méthode deleteById
    }

    @Override
    public RoleUserDTO partialUpdate(RoleUserDTO roleUserDTO, Long id) {
        return roleUserRepository.findById(id).map(roleUser -> {
            RoleUserMapping.partialUpdate(roleUser, roleUserDTO);
            return roleUser;
        }).map(roleUserRepository::save).map(roleUserMapper::ToDto).orElse(null);
    }

    @Override
    public RoleUserDTO saveRoleUser(RoleUserDTO roleUserDTO) {
        final String slug = SlugifyUtils.generate(roleUserDTO.getNameRole());
        roleUserDTO.setSlug(slug);
        return save(roleUserDTO);
    }


}
