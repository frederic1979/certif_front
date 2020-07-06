package co.simplon.bakerdelivery;

import co.simplon.bakerdelivery.model.AppUser;
import co.simplon.bakerdelivery.model.Role;
import co.simplon.bakerdelivery.service.AppUserService;
import co.simplon.bakerdelivery.service.CommandService;
import co.simplon.bakerdelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class BakerdeliveryApplication implements CommandLineRunner {

    @Qualifier("appUserServiceImpl")
    @Autowired
    AppUserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BakerdeliveryApplication.class, args);
    }

    /**
     * Init method that loads some data in database.
     *
     * @param params
     * @throws Exception
     */
    @Override
    public void run(String... params) throws Exception {
        AppUser admin = new AppUser("baker", "bakerdelivery", new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
        userService.signup(admin);

        AppUser seller = new AppUser("seller", "bakerdelivery", new ArrayList<>(Arrays.asList(Role.ROLE_SELLER)));
        userService.signup(seller);


    }


}
