package com.back_spring_boot_book.configuration;

import com.back_spring_boot_book.utils.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF (Cross-Site Request Forgery) est une attaque de sécurité qui exploite la confiance que les
        // sites web accordent aux utilisateurs authentifiés. L'attaque se produit lorsque des actions non autorisées
        // sont effectuées au nom d'un utilisateur authentifié sans son consentement. Cela peut se produire lorsqu'un utilisateur est
        // incité à cliquer sur un lien ou à soumettre un formulaire malveillant sur un site tiers pendant qu'il est connecté à un autre site.
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/connexion", "/inscription").permitAll() // Pas besoin d'autorisation pour y accéder
                .anyRequest().authenticated() // Besoin d'autorisation pour y accéder
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Désactiver la gestion de session

        // Ajout du filtre utilisé pour valider le token par la suite.
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // Configuration CORS (Cross-Origin Resource Sharing) est un mécanisme de
        // sécurité utilisé par les navigateurs web pour contrôler les requêtes HTTP entre
        // des origines différentes. Une origine est constituée du protocole, du nom de domaine et du port utilisés dans une URL.
        http.cors();
    }

    /**
     * Pour dire que l'on accepte les requètes de toutes origines.
     * @return corsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Autoriser toutes les origines, méthodes et en-têtes
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}