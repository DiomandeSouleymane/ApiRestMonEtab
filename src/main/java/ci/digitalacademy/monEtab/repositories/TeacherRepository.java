package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.models.enumeration.Gender;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findById(Long id);

    List<Teacher> findByLastNameOrSpecialtyAndGender(String lastName, String specialty, Gender gender);

    List<Teacher> findByLastNameOrSpecialtyAndGender(String query, String query1, ci.digitalacademy.monEtab.services.impl.Gender gender);

}