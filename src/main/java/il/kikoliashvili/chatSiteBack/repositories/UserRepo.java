package il.kikoliashvili.chatSiteBack.repositories;

import il.kikoliashvili.chatSiteBack.entities.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for users
 */
@Repository
public interface UserRepo extends JpaRepository <SiteUser, UUID> {
    /**
     * method for finding user by his username in database
     * @param username username
     * @return user
     */
    SiteUser findByUsername(String username);
    /**
     * method for finding user by his email in database
     * @param email email
     * @return user
     */
    SiteUser findByEmail(String email);
}
