package com.back_spring_boot_book.service;

import java.util.Optional;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;

/**
 * Interface de la classe ServiceUtilisateur
 */
public interface IServiceUtilisateur {

	/**
	 * Ajoute un utilisateur en BDD.
	 * @param utilisateur
	 * @return l'utilisateur rajouté
	 * @throws UtilisateurExisteDejaException 
	 */
	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws UtilisateurExisteDejaException;

	
	/**
	 * Trouve un utilisateur par login et password.
	 * @param login
	 * @param password
	 * @return l'utilisateur trouvé
	 * @throws UtilisateurNonTrouveException
	 */
	public Utilisateur findUtilisateurByLoginAndPassword(String login, String password) throws UtilisateurNonTrouveException;

	/**
	 * Trouve un utilisateur par login
	 * @param login
	 * @return l'utilisateur trouvé
	 */
	public Optional<Utilisateur> findUtilisateurByLogin(String login);

	/**
	 * Trouve un utilisateur par idUser
	 * @param idUser
	 * @return l'utilisateur trouvé
	 */
	public Utilisateur findUtilisateurById(Integer idUser) throws UtilisateurNonTrouveException;
}