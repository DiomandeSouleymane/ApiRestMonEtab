package ci.digitalacademy.monEtab.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "absence")
public class Absence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_absence;

    @Column(name = "date_absence" )
    private Date absenceDate;

    @Column(name = "number_absence")
    private Integer absenceNumber;

    @Column(unique = true)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private Student student;
}
