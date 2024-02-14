package com.back_spring_boot_book.service.serviceInterface;

import java.util.Optional;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;

/**
 * Interface de la classe ServiceUtilisateur
 */
public interface IServiceUtilisateur {

	/**
	 * Trouve un utilisateur par idUser
	 * @param idUser
	 * @return l'utilisateur trouvé
	 */
	public Utilisateur findUtilisateurById(Integer idUser) throws UtilisateurNonTrouveException;
}