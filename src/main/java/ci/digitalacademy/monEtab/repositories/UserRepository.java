package ci.digitalacademy.monEtab.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import ci.digitalacademy.monEtab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPseudo(String pseudo);

    List<User> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role);

    Optional<User> findBySlug(String slug);
}
