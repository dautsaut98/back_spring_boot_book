package com.back_spring_boot_book.outils;

import java.util.Collections;

import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.BookDTO;
import com.back_spring_boot_book.model.Session;
import com.back_spring_boot_book.model.SessionDTO;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.model.UtilisateurDTO;

public class Converter {
	public static Book convertBookDTOToBook(BookDTO bookDTO) {
		return new Book(bookDTO.getId(),
				null,
				Collections.emptyList(),
				bookDTO.getNom(),
				bookDTO.getPrenomAuteur(),
				bookDTO.getNomAuteur(),
				bookDTO.getDescription(),
				bookDTO.getDateParution(),
				bookDTO.isLu());
	}
	
	public static BookDTO convertBookToBookDTO(Book book) {
		return new BookDTO(book.getId(),
				book.getUtilisateur().getId(),
				book.getNom(),
				book.getPrenomAuteur(),
				book.getNomAuteur(),
				book.getDescription(),
				book.getDateParution(),
				book.isLu());
	}
	
	public static Utilisateur convertUtilisateurDTOToUtilisateur(UtilisateurDTO utilisateurDTO) {
		return new Utilisateur(utilisateurDTO.getId(),
				utilisateurDTO.getLogin(),
				utilisateurDTO.getPassword(),
				utilisateurDTO.getPrenom(),
				utilisateurDTO.getNom(),
				utilisateurDTO.getEmail(),
				Collections.emptyList());
	}
	
	public static UtilisateurDTO convertUtilisateurToUtilisateurDTO(Utilisateur utilisateur) {
		return new UtilisateurDTO(utilisateur.getId(),
				utilisateur.getLogin(),
				utilisateur.getPassword(),
				utilisateur.getPrenom(),
				utilisateur.getNom(),
				utilisateur.getEmail());
	}
	
	public static Session convertSessionDTOToSession(SessionDTO sessionDTO) {
		return new Session(sessionDTO.getId(),
				sessionDTO.getDate(),
				sessionDTO.getDuree(),
				sessionDTO.getNombreDePageLu(),
				null);
	}
	
	public static SessionDTO convertSessionToSessionDTO(Session session) {
		return new SessionDTO(session.getId(),
				session.getDate(),
				session.getDuree(),
				session.getNombreDePageLu(),
				session.getLivre().getId());
	}
}
