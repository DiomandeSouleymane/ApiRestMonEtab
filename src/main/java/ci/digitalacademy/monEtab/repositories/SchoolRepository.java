package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findById(Long id);
    Optional<School> findBySlug(String slug);
}
