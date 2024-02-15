package com.back_spring_boot_book.controlleur;

import com.back_spring_boot_book.dtos.requestDto.AuthentificationRequestDTO;
import com.back_spring_boot_book.dtos.requestDto.InscriptionRequestDTO;
import com.back_spring_boot_book.dtos.responseDto.AuthentificationResponseDTO;
import com.back_spring_boot_book.dtos.responseDto.InscriptionResponseDTO;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.service.serviceImplemente.UserServiceDetailsImpl;
import com.back_spring_boot_book.utils.converters.ConvertRequestDTOToEntity;
import com.back_spring_boot_book.utils.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceDetailsImpl userDetailsService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public ResponseEntity<AuthentificationResponseDTO> createAuthenticationToken(@Valid @RequestBody AuthentificationRequestDTO authenticationRequest) {
        logger.debug("Appel de createAuthenticationToken avec l utilisateur "+authenticationRequest.getLogin());
        UserDetails userDetails;
        try {
            // Récupération des informations de l'utilisateur à partir du service UserDetailsService
            userDetails = userDetailsService.loadUserByUsernameAndPassword(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        } catch (UtilisateurNonTrouveException e) {
            // Si une exception d'authentification est levée (par exemple, mauvais nom d'utilisateur ou mot de passe), renvoyer une réponse d'erreur
            logger.debug("Sortie 404 de createAuthenticationToken car l utilisateur "+authenticationRequest.getLogin()+" a un login ou password incorrect");
            return ResponseEntity.notFound().build();
        }

        // Si l'authentification réussit, génération d'un token JWT avec les informations de l'utilisateur
        String jwtToken = jwtUtil.generateToken(userDetails);

        // Retourner une réponse avec le token JWT dans un objet DTO (Data Transfer Object)
        logger.debug("Sortie 200 de createAuthenticationToken avec l utilisateur "+authenticationRequest.getLogin());
        return ResponseEntity.ok(new AuthentificationResponseDTO(jwtToken));
    }

    @PostMapping("/inscription")
    public ResponseEntity<InscriptionResponseDTO> addUtilisateur(@Valid @RequestBody InscriptionRequestDTO newUtilisateurRequest) {
        logger.debug("Appel de addUtilisateur avec l utilisateur "+newUtilisateurRequest.toString());
        UserDetails userDetails = null;
        try {
            Utilisateur newUtilisateur = ConvertRequestDTOToEntity.convertInscriptionRequestDTOToUtilisateur(newUtilisateurRequest);
            userDetails = userDetailsService.saveUser(newUtilisateur);
        } catch (UtilisateurNonTrouveException e) {
            // Si une exception d'authentification est levée (par exemple, mauvais nom d'utilisateur ou mot de passe), renvoyer une réponse d'erreur
            logger.debug("Sortie 404 de addUtilisateur car l utilisateur "+newUtilisateurRequest.getLogin()+" existe deja en bdd");
            return ResponseEntity.notFound().build();
        }
        // Si l'authentification réussit, génération d'un token JWT avec les informations de l'utilisateur
        String jwtToken = jwtUtil.generateToken(userDetails);

        // Retourner une réponse avec le token JWT dans un objet DTO (Data Transfer Object)
        logger.debug("Sortie 200 de createAuthenticationToken avec l utilisateur "+userDetails.getUsername());
        return ResponseEntity.ok(new InscriptionResponseDTO(jwtToken));
    }
}