package co.simplon.bakerdelivery.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * App User entity that represents an application user.
 */

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roleList;

    public AppUser() {
    }


    public AppUser(@NotNull String username, @NotNull String password, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }
}
