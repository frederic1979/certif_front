package co.simplon.bakerdelivery.dto;

import co.simplon.bakerdelivery.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Specific App User DTO to be able to send user data without password through REST responses.
 */
public class AppUserDto {

    private Long id;

    private String username;

    private List<Role> roleList;

    public AppUserDto(@NotNull String username, List<Role> roleList) {
        this.username = username;
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Role> getRoleList() {
        return roleList;
    }
}
