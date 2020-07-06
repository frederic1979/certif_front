package co.simplon.bakerdelivery.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


 /*on va essayer de recup le token de la req http et on va le valider
   si pas de token, on authentifie pas l'utilisateur'
 ou si y a une exceptio qd on essaie de valider le token--> on nettoie le contexte de secutité,
 s 'assurer que oersonne authent sur notre app'*/
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
                                    throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {

                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ex) {

            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
