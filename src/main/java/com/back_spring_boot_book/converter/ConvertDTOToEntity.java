package com.back_spring_boot_book.converter;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.BookDTO;
import com.back_spring_boot_book.model.Session;
import com.back_spring_boot_book.model.SessionDTO;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.model.UtilisateurDTO;

public class ConvertDTOToEntity {

	public static Book convertBookDTOToBook(BookDTO bookDTO) {
		if(Objects.isNull(bookDTO)) {
			return null;
		}

		return Book.builder()
				.id(bookDTO.getId())
				.sessions(Collections.emptyList())
				.nom(bookDTO.getNom())
				.prenomAuteur(bookDTO.getPrenomAuteur())
				.nomAuteur(bookDTO.getNomAuteur())
				.description(bookDTO.getDescription())
				.dateParution(bookDTO.getDateParution())
				.lu(bookDTO.isLu())
				.build();
	}
	
	public static Utilisateur convertUtilisateurDTOToUtilisateur(UtilisateurDTO utilisateurDTO) {
		if(Objects.isNull(utilisateurDTO)) {
			return null;
		}

		return Utilisateur.builder()
				.id(utilisateurDTO.getId())
				.login(utilisateurDTO.getLogin())
				.prenom(utilisateurDTO.getPrenom())
				.nom(utilisateurDTO.getNom())
				.email(utilisateurDTO.getEmail())
				.build();
	}
	
	public static Session convertSessionDTOToSession(SessionDTO sessionDTO) {
		if(Objects.isNull(sessionDTO)) {
			return null;
		}

		return Session.builder()
				.id(sessionDTO.getId())
				.date(sessionDTO.getDate())
				.duree(sessionDTO.getDuree())
				.nombreDePageLu(sessionDTO.getNombreDePageLu())
				.build();
	}
}
