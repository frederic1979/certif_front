package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.model.AppUser;
import co.simplon.bakerdelivery.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<AppUser> user = userRepository.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("AppUser '" + username + "' not found");
        }

        return User
                .withUsername(username)
                .password(user.get().getPassword())
                .authorities(user.get().getRoleList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
