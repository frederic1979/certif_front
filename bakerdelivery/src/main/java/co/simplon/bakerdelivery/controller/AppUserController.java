package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.AppUserDto;
import co.simplon.bakerdelivery.dto.JsonWebToken;
import co.simplon.bakerdelivery.exception.ExistingUsernameException;
import co.simplon.bakerdelivery.model.AppUser;
import co.simplon.bakerdelivery.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This controller is in charge of responding to http calls on /api/users.
 * It manages user registration, user authentication and can provide user related data to Admin users.
 */
@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;


    /*public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }*/

    /**
     * Method to register a new user in database.
     *
     * @param user the new user to create.
     * @return a JWT if sign up is ok, a bad response code otherwise.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<JsonWebToken> signUp(@RequestBody AppUser user) {
        try {
            return ResponseEntity.ok(new JsonWebToken(appUserService.signup(user)));
        } catch (ExistingUsernameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Method to sign in a user (already existing).
     *
     * @param user the user to sign in to the app.
     * @return a JWT if sign in is ok, a bad response code otherwise.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebToken> signIn(@RequestBody AppUser user) {
        try {
            return ResponseEntity.ok(new JsonWebToken(appUserService.signin(user.getUsername(), user.getPassword())));

        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Method to get all users from the database.
     * This method is restricted to Admin users.
     *
     * @return the list of all users registered in the database.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AppUserDto> getAllUsers() {
        return appUserService.findAllUsers().stream().map(appUser -> new AppUserDto(appUser.getUsername(), appUser.getRoleList())).collect(Collectors.toList());
    }

    /**
     * Method to get one user from database based on its user name.
     * This method is restricted to Admin users.
     *
     * @param appUserName the user name to look for.
     * @return a User object if found, a not found response code otherwise.
     */
    @GetMapping("/{appUserName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUserDto> getOneUser(@PathVariable String appUserName) {
        Optional<AppUser> appUser = appUserService.findUserByUserName(appUserName);
        if (appUser.isPresent()) {
            return ResponseEntity.ok(new AppUserDto(appUser.get().getUsername(), appUser.get().getRoleList()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
