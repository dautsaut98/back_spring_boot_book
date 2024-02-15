package com.back_spring_boot_book.utils.jwt;

import com.back_spring_boot_book.service.serviceImplemente.UserServiceDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Permet de gérer la validation du token.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // Clé secrète utilisée pour signer et valider les tokens JWT
    @Value("${jwt.secret}")
    private String secret;

    // Service utilisé pour charger les détails de l'utilisateur à partir du nom d'utilisateur
    private final UserServiceDetailsImpl userDetailsService;

    @Autowired
    public JwtRequestFilter(UserServiceDetailsImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Méthode pour intercepter chaque requête entrante
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Récupération de l'en-tête d'autorisation qui contient le token JWT
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Vérification si l'en-tête d'autorisation est présent et commence par "Bearer "
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            // Extrait le token JWT de l'en-tête
            jwt = authorizationHeader.substring(7);
            try {
                // Vérification et extraction du nom d'utilisateur à partir du token JWT
                username = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
            } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | ExpiredJwtException e) {
                // En cas d'erreur lors de la validation du token JWT
                logger.error("Invalid or expired JWT token");
            }
        }

        // Vérification si le nom d'utilisateur est extrait du token et si l'authentification n'est pas déjà configurée
        if (StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;
            try {
                // Chargement des détails de l'utilisateur à partir du nom d'utilisateur
                userDetails = userDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                // En cas d'utilisateur non trouvé dans la base de données
                logger.error("User not found");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Vérification de la validité du token JWT
            if (jwt != null && Jwts.parser().setSigningKey(secret).isSigned(jwt)) {
                // Création de l'authentification et configuration dans le contexte de sécurité
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Poursuite de la chaîne de filtres
        chain.doFilter(request, response);
    }
}
