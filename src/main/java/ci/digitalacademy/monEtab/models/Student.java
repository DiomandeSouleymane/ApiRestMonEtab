package ci.digitalacademy.monEtab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "student")
public class Student extends Person {

    @Column( name = "matricule")
    private String matricule;
    @Column(name = "phone_number_parent" )
    private String phoneNumberFather;
    @Column(name = "classe")
    private String classe;
    private String slug;
    @OneToMany(  mappedBy = "student")
    @Column(nullable = true)
    private List<Absence> absence;



}
