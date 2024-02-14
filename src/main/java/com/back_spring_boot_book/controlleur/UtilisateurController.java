package com.back_spring_boot_book.controlleur;

import com.back_spring_boot_book.utils.converters.ConvertRequestDTOToEntity;
import com.back_spring_boot_book.utils.converters.ConverterEntityToResponseDTO;
import com.back_spring_boot_book.dtos.requestDto.UtilisateurRequestDTO;
import com.back_spring_boot_book.dtos.responseDto.UtilisateurResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.service.serviceImplemente.ServiceUtilisateur;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;

    Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
}