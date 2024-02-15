package com.back_spring_boot_book.controlleur;

import com.back_spring_boot_book.dtos.responseDto.UtilisateurResponseDTO;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.utils.converters.ConverterEntityToResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.back_spring_boot_book.service.serviceImplemente.ServiceUtilisateur;

import javax.validation.Valid;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;

    Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @GetMapping("/{login}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurByLogin(@Valid @PathVariable String login) {
        logger.debug("Appel de getUtilisateurByLogin avec l utilisateur "+login);
        try {
            UtilisateurResponseDTO utilisateurResponseDTO = ConverterEntityToResponseDTO.convertUtilisateurToUtilisateurDTO(this.serviceUtilisateur.findUtilisateurByLogin(login));
            logger.debug("Sortie 200 de getUtilisateurByLogin avec l utilisateur "+utilisateurResponseDTO);
            return ResponseEntity.ok(utilisateurResponseDTO);
        } catch (UtilisateurNonTrouveException e) {
            logger.debug("Sortie 404 de getUtilisateurByLogin car l utilisateur "+login+" est introuvable en bdd");
            return ResponseEntity.notFound().build();
        }
    }
}