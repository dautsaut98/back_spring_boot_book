package com.back_spring_boot_book.utils.converters;

import java.util.Objects;
import java.util.Optional;

import com.back_spring_boot_book.dtos.responseDto.BookResponseDTO;
import com.back_spring_boot_book.dtos.responseDto.SessionResponseDTO;
import com.back_spring_boot_book.dtos.responseDto.UtilisateurResponseDTO;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.Session;
import com.back_spring_boot_book.model.Utilisateur;

public class ConverterEntityToResponseDTO {

	public static BookResponseDTO convertBookToBookDTO(Book book) {
		if(Objects.isNull(book)) {
			return null;
		}

		return BookResponseDTO.builder()
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

	public static UtilisateurResponseDTO convertUtilisateurToUtilisateurDTO(Utilisateur utilisateur) {
		if(Objects.isNull(utilisateur)) {
			return null;
		}

		return UtilisateurResponseDTO.builder()
				.id(utilisateur.getId())
				.login(utilisateur.getLogin())
				.prenom(utilisateur.getPrenom())
				.nom(utilisateur.getNom())
				.email(utilisateur.getEmail())
				.build();
	}

	public static SessionResponseDTO convertSessionToSessionDTO(Session session) {
		if(Objects.isNull(session)) {
			return null;
		}

		return SessionResponseDTO.builder()
				.id(session.getId())
				.idlivre(Optional.ofNullable(session.getLivre()).map(Book::getId).orElse(null))
				.date(session.getDate())
				.duree(session.getDuree())
				.nombreDePageLu(session.getNombreDePageLu())
				.build();
	}
}
