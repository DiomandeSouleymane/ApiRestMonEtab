package ci.digitalacademy.monEtab.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTTokenDTO {

    @JsonProperty("id_token")
    private String IdToken;

}
