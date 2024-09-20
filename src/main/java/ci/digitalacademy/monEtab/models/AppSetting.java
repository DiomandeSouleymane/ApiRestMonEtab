package ci.digitalacademy.monEtab.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "app_setting")
public class AppSetting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false , name = "smtp_server")
    private String smtpServer;

    @Column( nullable = false , name = "smtp_port")
    private Integer smtpPort;

    @Column( nullable = false , name = "smtp_username")
    private String smtpUsername;

    @Column(unique = true, nullable = false , name = "smtp_password")
    private String smtpPassword;

    private String slug;

    @OneToOne(mappedBy = "appSetting")
    private School school;


    // Getters et Setters
}