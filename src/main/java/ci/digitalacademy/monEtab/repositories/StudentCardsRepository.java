package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.models.StudentCards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCardsRepository extends JpaRepository<StudentCards, Long> {

    Optional<StudentCards> findById(Long id);
    Optional<StudentCards> findBySlug(String slug);
}
