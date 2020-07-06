package co.simplon.bakerdelivery.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_SELLER;

    @Override
    public String getAuthority() {
        return name();
    }

}
