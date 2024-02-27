package com.back_spring_boot_book.service.serviceImplemente;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.aol.cyclops.matcher.Predicates;
import com.back_spring_boot_book.exceptions.BookNonTrouveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.back_spring_boot_book.exceptions.BookExisteDejaException;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.repository.RepositoryBook;
import com.back_spring_boot_book.service.serviceInterface.IServiceBook;

import javax.swing.*;

@Service
public class ServiceBook implements IServiceBook {

	// Repository des livres.
	private final RepositoryBook bookRepository;

	Logger logger = LoggerFactory.getLogger(ServiceBook.class);

	public ServiceBook(RepositoryBook bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public void addBook(Book book) throws BookExisteDejaException {
		logger.debug("Entree dans la méthode addBook avec le livre : " + book.toString());
		this.findBookByNomAndIdUtilisateur(book.getNom(), book.getUtilisateur().getId())
				.ifPresent(user -> {throw new BookExisteDejaException("le livre avec le nom "+book.getNom()+" existe deja");});
		this.bookRepository.save(book);
		logger.debug("Sortie OK de la méthode addBook avec le livre : "+ book);
	}

	@Override
	public List<Book> findByIdUtilisateur(Integer idUtilisateur) {
		logger.debug("Entree dans la méthode findByIdUtilisateur avec l id utilisateur : " + idUtilisateur);
		List<Book> livres = this.bookRepository.findAllByUtilisateurId(idUtilisateur).orElseGet(Collections::emptyList);
		logger.debug("Sortie OK de la méthode findByIdUtilisateur avec l id utilisateur : "+idUtilisateur+" et les livres : "+ livres);
		return livres;
	}

	@Override
	public Optional<Book> findBookByNomAndIdUtilisateur(String nom, Integer idUtilisateur) {
		logger.debug("Entree dans la méthode findBookByNomAndIdUtilisateur avec l id utilisateur : "+idUtilisateur);
		Optional<Book> opLivre = this.bookRepository.findByNomAndUtilisateurId(nom, idUtilisateur);
		if(opLivre.isEmpty())
			logger.debug("Sortie OK de la méthode findBookByNomAndIdUtilisateur avec le livre avec comme nom : " + nom + " et id utilisateur : "+idUtilisateur+" absent");
		else
			logger.debug("Sortie OK de la méthode findBookByNomAndIdUtilisateur pour l id utilisateur "+idUtilisateur+" et livre : "+opLivre.get());
		return opLivre;
	}

	@Override
	public Optional<Book> findBookByIdAndIdUtilisateur(Integer idBook, Integer idUtilisateur) {
		logger.debug("Entree dans la méthode findBookByIdAndIdUtilisateur avec l id utilisateur : "+idUtilisateur);
		Optional<Book> opLivre = this.bookRepository.findByIdAndUtilisateurId(idBook, idUtilisateur);
		if(opLivre.isEmpty())
			logger.debug("Sortie OK de la méthode findBookByIdAndIdUtilisateur avec le livre avec comme id : " + idBook + " et id utilisateur : "+idUtilisateur+" absent");
		else
			logger.debug("Sortie OK de la méthode findBookByIdAndIdUtilisateur pour l id utilisateur "+idUtilisateur+" et livre : "+opLivre.get());
		return opLivre;
	}

	@Override
	public Book updateBook(Book bookWithDataUpdate, Integer idUtilisateur) throws BookExisteDejaException {
		logger.debug("Entree dans la méthode updateBook avec le livre : " + bookWithDataUpdate.toString());
		Book bookFindBdd = this.findBookByIdAndIdUtilisateur(bookWithDataUpdate.getId(), idUtilisateur)
				.orElseThrow(() -> new BookNonTrouveException("livre non trouvé"));
		this.findBookByNomAndIdUtilisateur(bookWithDataUpdate.getNom(), idUtilisateur)
				.filter(book -> !book.getId().equals(bookWithDataUpdate.getId()))
				.ifPresent((book) -> {throw new BookExisteDejaException("le livre existe dejà");});
		bookFindBdd.setLu(bookWithDataUpdate.isLu());
		bookFindBdd.setNom(bookWithDataUpdate.getNom());
		bookFindBdd.setNomAuteur(bookWithDataUpdate.getNomAuteur());
		bookFindBdd.setPrenomAuteur(bookWithDataUpdate.getPrenomAuteur());
		bookFindBdd.setDateParution(bookWithDataUpdate.getDateParution());
		bookFindBdd.setDescription(bookWithDataUpdate.getDescription());
		bookFindBdd.setSrcImage(bookWithDataUpdate.getSrcImage());
		Book bookRetour = this.bookRepository.save(bookFindBdd);
		logger.debug("Sortie OK de la méthode updateBook avec le livre : "+ bookFindBdd);
		return bookRetour;
	}

	@Override
	public void deleteBook(Integer idLivre) throws BookNonTrouveException {
		this.bookRepository
				.findById(idLivre)
				.orElseThrow(() -> new BookNonTrouveException("livre non trouve"));
		this.bookRepository.deleteById(idLivre);
	}
}