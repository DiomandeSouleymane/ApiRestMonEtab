package ci.digitalacademy.monEtab.repositories;

import ci.digitalacademy.monEtab.models.Student;
import ci.digitalacademy.monEtab.models.enumeration.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(String lastName, String matricule, Gender gender);

    List<Student> findByLastNameIgnoreCaseOrMatriculeIgnoreCase(String lastName, String matricule);
}

