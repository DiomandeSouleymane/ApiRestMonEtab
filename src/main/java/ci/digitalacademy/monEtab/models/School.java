package ci.digitalacademy.monEtab.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.convert.ReadingConverter;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_school;
    @Column(nullable = false)
    private String nameSchool;
    @Column(nullable = false , name = "url_logo")
    private String urlLogo;

    private String slug;

    @OneToOne
    @JoinColumn(name = "app_setting_id", referencedColumnName = "id")
    private AppSetting appSetting;


}
