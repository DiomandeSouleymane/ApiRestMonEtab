package ci.digitalacademy.monEtab.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "student_crds")
public class StudentCards implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStudentCard;

    @Column(nullable = false , name = "reference")
    private String reference;

    @Column(nullable = false , name = "issue_date")
    private Date issueDate;
    private String slug;
    @Column(nullable = false , name = "expiry_date")
    private Date expirationDate;

    @ManyToOne
    private Student student;
}
