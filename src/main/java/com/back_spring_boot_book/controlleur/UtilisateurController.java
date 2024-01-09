package com.back_spring_boot_book.controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.model.UtilisateurDTO;
import com.back_spring_boot_book.outils.Converter;
import com.back_spring_boot_book.service.ServiceUtilisateur;

@RestController
public class UtilisateurController {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;

    @GetMapping("/utilisateur")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@RequestParam String login, @RequestParam String password) {
        try {
            UtilisateurDTO utilisateurDTO = Converter.convertUtilisateurToUtilisateurDTO(this.serviceUtilisateur.findUtilisateurByLoginAndPassword(login, password));
            return ResponseEntity.ok(utilisateurDTO);
        } catch (UtilisateurNonTrouveException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/utilisateur")
    public ResponseEntity<UtilisateurDTO> addUtilisateur(@RequestBody Utilisateur newUtilisateur) {
        try {
        	newUtilisateur.setId(null);
        	UtilisateurDTO utilisateurDTO = Converter.convertUtilisateurToUtilisateurDTO(this.serviceUtilisateur.addUtilisateur(newUtilisateur));
            return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurDTO);
        } catch (UtilisateurExisteDejaException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
