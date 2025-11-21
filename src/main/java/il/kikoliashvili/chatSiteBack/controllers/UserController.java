package il.kikoliashvili.chatSiteBack.controllers;

import il.kikoliashvili.chatSiteBack.entities.SiteUser;
import il.kikoliashvili.chatSiteBack.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Users (register, login)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    /** userService bean */
    private final UserService userService;

    /**
     * REST point for register users
     * @param siteUser - user for registration
     * @return description of what happened
     */
    @PostMapping("/register")
    @Operation(summary = "Register user")
    public String registerUser(@RequestBody @Parameter(
            name = "user",
            description = "user to register") SiteUser siteUser) {
        try {
            return userService.registerUser(siteUser);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * REST point for login users
     * @param username username for login
     * @param password password of user
     * @return description of what happened
     */
    @PostMapping("/login")
    @Operation(summary = "login user")
    public String loginUser(
            @RequestParam @Parameter(
                    name = "username",
                    description = "username of user",
                    example = "username",
                    required = true) String username,
            @RequestParam @Parameter(
                    name = "password",
                    description = "password of user",
                    example = "password",
                    required = true) String password) {
        try {
            return userService.login(username, password);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
