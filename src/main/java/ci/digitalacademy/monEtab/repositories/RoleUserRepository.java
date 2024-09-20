package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {

    List<RoleUser> findByNameRole(String roleName);
    Optional<RoleUser> findById(Long id);
    Optional<RoleUser> findBySlug(String slug);

}
