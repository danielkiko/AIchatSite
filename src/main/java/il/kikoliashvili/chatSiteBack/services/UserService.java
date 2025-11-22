package il.kikoliashvili.chatSiteBack.services;

import com.password4j.Password;
import il.kikoliashvili.chatSiteBack.entities.SiteUser;
import il.kikoliashvili.chatSiteBack.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * service for saving and searching users
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /** salt for encryption */
    @Value("${securityParameters.salt}")
    private String salt;
    /** pepper for encryption */
    @Value("${securityParameters.pepper}")
    private String pepper;
    /** user repo bean */
    private final UserRepo userRepo;

    /**
     * method to register user
     * @param siteUser user
     * @return description of what happened (is it registered or error)
     * @throws Exception if there is an error
     */
    public String registerUser(SiteUser siteUser) throws Exception {
        try {
            // add password encryption, add email check, add username check
            if(getUserByEmail(siteUser.getEmail()) != null)
            {
                return "Email Already Exists";
            }

            if (getUserByUsername(siteUser.getUsername()) != null)
            {
                return "Username Already Exists";
            }
            siteUser.setPassword(encryptPassword(siteUser.getPassword()));

            userRepo.save(siteUser);
        } catch (Exception e) {
            String message = "addUser ->" + e.getMessage();
            throw new Exception(message);
        }
        return "User created";
    }

    /**
     * method for password encryption
     * @param password password to encrypt
     * @return encrypted password
     */
    private String encryptPassword(String password) {
        return Password.hash(password).addSalt(salt).addPepper(pepper).withScrypt().getResult();
    }

    /**
     * method to get user from database by username
     * @param username username
     * @return user
     */
    public SiteUser getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    /**
     * method to get user from database by email
     * @param email email
     * @return user
     */
    private SiteUser getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * method to login
     * @param username username
     * @param password password
     * @return description of what happened or error
     */
    public String login(String username, String password) {
        // find username in db
        SiteUser siteUser = userRepo.findByUsername(username);
        if(siteUser == null){
            return "User not found";
        }
        //check password
        if(checkPass(siteUser, password)) {
            return "Successfully logged in";
        }
        return "Invalid username or password";
    }

    /**
     * method to check password for user
     * @param user user that tries to log in
     * @param passwordToCheck password that we need to check
     * @return true if password is right, else if not
     */
    private boolean checkPass(SiteUser user, String passwordToCheck) {
        String realPass = user.getPassword();
        return Password.check(passwordToCheck, realPass).addSalt(salt).addPepper(pepper).withScrypt();
    }
}
