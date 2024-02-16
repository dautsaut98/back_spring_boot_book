package com.back_spring_boot_book.utils.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Objects;

import com.back_spring_boot_book.dtos.requestDto.BookRequestDTO;
import com.back_spring_boot_book.dtos.requestDto.InscriptionRequestDTO;
import com.back_spring_boot_book.dtos.requestDto.UtilisateurRequestDTO;
import com.back_spring_boot_book.dtos.responseDto.SessionResponseDTO;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.Session;
import com.back_spring_boot_book.model.Utilisateur;

public class ConvertRequestDTOToEntity {

	public static Book convertBookDTOToBook(BookRequestDTO bookRequestDTO) throws ParseException {
		if(Objects.isNull(bookRequestDTO)) {
			return null;
		}

		return Book.builder()
				.id(bookRequestDTO.getId())
				.sessions(Collections.emptyList())
				.nom(bookRequestDTO.getNom())
				.prenomAuteur(bookRequestDTO.getPrenomAuteur())
				.nomAuteur(bookRequestDTO.getNomAuteur())
				.description(bookRequestDTO.getDescription())
				.dateParution(new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
						.parse(bookRequestDTO.getDateParution())
						.getTime()))
				.lu(bookRequestDTO.isLu())
				.srcImage(bookRequestDTO.getSrcImage())
				.build();
	}
	
	public static Utilisateur convertUtilisateurDTOToUtilisateur(UtilisateurRequestDTO utilisateurRequestDTO) {
		if(Objects.isNull(utilisateurRequestDTO)) {
			return null;
		}

		return Utilisateur.builder()
				.id(utilisateurRequestDTO.getId())
				.login(utilisateurRequestDTO.getLogin())
				.prenom(utilisateurRequestDTO.getPrenom())
				.nom(utilisateurRequestDTO.getNom())
				.password(utilisateurRequestDTO.getPassword())
				.email(utilisateurRequestDTO.getEmail())
				.build();
	}

	public static Utilisateur convertInscriptionRequestDTOToUtilisateur(InscriptionRequestDTO inscriptionRequestDTO) {
		if(Objects.isNull(inscriptionRequestDTO)) {
			return null;
		}

		return Utilisateur.builder()
				.login(inscriptionRequestDTO.getLogin())
				.prenom(inscriptionRequestDTO.getPrenom())
				.nom(inscriptionRequestDTO.getNom())
				.password(inscriptionRequestDTO.getPassword())
				.email(inscriptionRequestDTO.getEmail())
				.build();
	}
	
	public static Session convertSessionDTOToSession(SessionResponseDTO sessionResponseDTO) {
		if(Objects.isNull(sessionResponseDTO)) {
			return null;
		}

		return Session.builder()
				.id(sessionResponseDTO.getId())
				.date(sessionResponseDTO.getDate())
				.duree(sessionResponseDTO.getDuree())
				.nombreDePageLu(sessionResponseDTO.getNombreDePageLu())
				.build();
	}
}
