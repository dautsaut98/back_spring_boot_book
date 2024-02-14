package com.back_spring_boot_book.service.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.back_spring_boot_book.exceptions.BookExisteDejaException;
import com.back_spring_boot_book.model.Book;

/**
 * Interface de la classe ServiceBook
 */
public interface IServiceBook {

	/**
	 * Ajoute un livre en BDD.
	 * @param book le livre à ajouter
	 * @throws BookExisteDejaException
	 */
	public void addBook(Book book) throws BookExisteDejaException;

	/**
	 * Trouve la liste des livres d'un utilisateur.
	 * @param idUtilisateur
	 * @return la liste des livres
	 */
	public List<Book> findByIdUtilisateur(Integer idUtilisateur);

	/**
	 * Trouve le livre en fonction du nom
	 * @param nom
	 * @param idUtilisateur
	 * @return le livre
	 */
	public Optional<Book> findBookByNomAndIdUtilisateur(String nom, Integer idUtilisateur);
}