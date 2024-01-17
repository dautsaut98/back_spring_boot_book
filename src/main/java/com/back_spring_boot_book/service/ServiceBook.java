package com.back_spring_boot_book.service;

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
		if (this.findBookByNomAndIdUtilisateur(book.getNom(), book.getUtilisateur().getId()).isPresent()) {
			String messageErreur = "le livre avec le nom "+book.getNom()+" existe deja";
			logger.error(messageErreur);
			throw new BookExisteDejaException(messageErreur);
		}
		this.bookRepository.save(book);
		logger.info("le livre avec le nom "+book.getNom()+" est enregistré");
	}

	@Override
	public List<Book> findByIdUtilisateur(Integer idUtilisateur) {
		List<Book> livres = this.bookRepository.findAllByUtilisateurId(idUtilisateur).orElseGet(Collections::emptyList);
		logger.info("liste des livres trouvés pour l utilisateur "+idUtilisateur+" est : "+livres.toString());
		return livres;
	}

	@Override
	public Optional<Book> findBookByNomAndIdUtilisateur(String nom, Integer idUtilisateur) {
		Optional<Book> opLivre = this.bookRepository.findByNomAndUtilisateurId(nom, idUtilisateur);
		logger.info("si le livre avec le nom "+nom+" trouvé pour l utilisateur "+idUtilisateur+" est trouvé : "+opLivre.isPresent());
		opLivre.ifPresent(livreFind -> logger.info("livre avec le nom "+nom+" trouvé pour l utilisateur "+idUtilisateur+" est : "+livreFind.toString()));
		return opLivre;
	}
}