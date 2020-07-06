package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    /*find an appUser with that username*/
    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);

}
