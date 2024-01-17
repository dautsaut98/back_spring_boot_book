package com.back_spring_boot_book.converter;

import java.util.Objects;
import java.util.Optional;

import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.BookDTO;
import com.back_spring_boot_book.model.Session;
import com.back_spring_boot_book.model.SessionDTO;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.model.UtilisateurDTO;

public class ConverterEntityToDTO {

	public static BookDTO convertBookToBookDTO(Book book) {
		if(Objects.isNull(book)) {
			return null;
		}

		return BookDTO.builder()
				.id(book.getId())
				.idUser(Optional.ofNullable(book.getUtilisateur()).map(Utilisateur::getId).orElse(null))
				.nom(book.getNom())
				.prenomAuteur(book.getPrenomAuteur())
				.nomAuteur(book.getNomAuteur())
				.description(book.getDescription())
				.dateParution(book.getDateParution())
				.lu(book.isLu())
				.build();
	}

	public static UtilisateurDTO convertUtilisateurToUtilisateurDTO(Utilisateur utilisateur) {
		if(Objects.isNull(utilisateur)) {
			return null;
		}

		return UtilisateurDTO.builder()
				.id(utilisateur.getId())
				.login(utilisateur.getLogin())
				.prenom(utilisateur.getPrenom())
				.nom(utilisateur.getNom())
				.email(utilisateur.getEmail())
				.build();
	}

	public static SessionDTO convertSessionToSessionDTO(Session session) {
		if(Objects.isNull(session)) {
			return null;
		}

		return SessionDTO.builder()
				.id(session.getId())
				.idlivre(Optional.ofNullable(session.getLivre()).map(Book::getId).orElse(null))
				.date(session.getDate())
				.duree(session.getDuree())
				.nombreDePageLu(session.getNombreDePageLu())
				.build();
	}
}
