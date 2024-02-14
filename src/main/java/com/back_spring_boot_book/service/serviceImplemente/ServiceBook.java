package com.back_spring_boot_book.service.serviceImplemente;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.back_spring_boot_book.exceptions.BookExisteDejaException;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.repository.RepositoryBook;
import com.back_spring_boot_book.service.serviceInterface.IServiceBook;

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
}