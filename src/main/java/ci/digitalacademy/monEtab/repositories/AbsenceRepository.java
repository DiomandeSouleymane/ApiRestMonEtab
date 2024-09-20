package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Absence;
import ci.digitalacademy.monEtab.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    Optional<Absence> findById(Long id);
    Optional<Absence> findBySlug(String slug);
}
