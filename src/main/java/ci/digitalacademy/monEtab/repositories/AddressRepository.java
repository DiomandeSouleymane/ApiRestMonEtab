package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findById(Long id);
    Optional<Address> findBySlug(String slug);

}
