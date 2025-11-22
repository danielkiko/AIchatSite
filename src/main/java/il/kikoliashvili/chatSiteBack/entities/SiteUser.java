package il.kikoliashvili.chatSiteBack.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Class for users of the app
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SiteUser implements UserDetails {
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
    /** is user enabled */
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}
