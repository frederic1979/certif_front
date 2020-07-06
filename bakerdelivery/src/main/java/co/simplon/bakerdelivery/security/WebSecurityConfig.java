package co.simplon.bakerdelivery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/*spring security a une config par defaut et on vient dire ce qu'on veut appliquer comme configuration'*/

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*permet par la suite de définir des règles d'autorisations directement au niveau des méthodes de nos controllers*/



    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Method that configures HTTP security.
     *
     * @param http the HttpSecurity object to configure.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.cors();

        // Disable CSRF (cross site request forgery as our token will be stored in session storage)
        /*on desactive la securité CSRF qui est un type d'attaque qui est utilisé qd on a une authent qu'on stock
            dans les cookies, et comme on utilise un token on peut la desactiv, on ne stock pas notre token dans les
            cookies, on est pas sujet à cette faille là*/
        http.csrf().disable();

        // Add handler to answer 401 when not authenticated
        /*on ajoute un unauthorizedHandler
        si jamais on a un token invalide ou qu'on a pas de token alors il faut renvoyer une erreur 401(unauthorize)
        car de base SpringSecurity nous renvoi une erreur 403 (forbidden, on est authent mais on a pas les droits)'*/
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        // No session will be created or used by spring security
        /*pas la peine de se rappeler de l'utilisateur,
        qd il se connecte, il envoi le token, pas la peine de savoir s'il s'est connecté
        c le principe des tokens, on va pouvoir verifier à chaque fois qu'il y a une requete
        que la connexion est autorisée, pas besoin de garder l'info des utilisateurs qui sont connectés*/
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()//
                .antMatchers("/api/users/sign-in").permitAll()
                .antMatchers("/api/restaurants/sign-up").permitAll()
                .antMatchers("/api/restaurants/sign-up").permitAll()
                .antMatchers("/api/bakerdelivery/commands/**").permitAll()
                .antMatchers("/api/bakerdelivery/restaurants/**").permitAll()//

                .antMatchers("/api/bakerdelivery/matrix").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/bakerdelivery/weeks").access("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")




                .anyRequest().authenticated();

        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Method that configures web security. Useful here for development purposes to allow h2 console access.
     *
     * @param web the WebSecurity object to configure.
     * @throws Exception
     */

 /*   j'ai reconfigué la WebSecurity pour pouvoir servir un front Angular compilé  plaçé sur un back
 * dans le cas ou on fait un package avec front et back */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**/**");

        web.ignoring().antMatchers("/");
        web.ignoring().antMatchers("/index.html");
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/main.js");
        web.ignoring().antMatchers("/polyfills.js");
        web.ignoring().antMatchers("/runtime.js");
        web.ignoring().antMatchers("/styles.js");
        web.ignoring().antMatchers("/vendor.js");
    }

    /**
     * Generic configuration for CORS. Useful here for development purposes as front is developed with Angular.
     *
     * @return the CorsConfigurationSource object.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));

        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));

        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


}
