package il.kikoliashvili.chatSiteBack.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Class for users of the app
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SiteUser {
    /** id of the user */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    /** username of the user */
    @Schema(name = "username", example = "username", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    /** password of the user */
    @Schema(name = "password", example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    /** email of the user */
    @Schema(name = "email", example = "email@google.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
