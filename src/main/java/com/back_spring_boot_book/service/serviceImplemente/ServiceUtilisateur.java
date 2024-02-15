package com.back_spring_boot_book.service.serviceImplemente;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.repository.RepositoryUtilisateur;
import com.back_spring_boot_book.service.serviceInterface.IServiceUtilisateur;

@Service
public class ServiceUtilisateur implements IServiceUtilisateur{

	// Repository des utilisateurs.
	private final RepositoryUtilisateur utilisateurRepository;

	Logger logger = LoggerFactory.getLogger(ServiceUtilisateur.class);

	public ServiceUtilisateur(RepositoryUtilisateur utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public Utilisateur findUtilisateurById(Integer idUser) throws UtilisateurNonTrouveException {
		logger.debug("Entree dans la méthode findUtilisateurById avec l idUser : " + idUser);
		Utilisateur utilisateur = this.utilisateurRepository
				.findById(idUser)
				.orElseThrow(() -> new UtilisateurNonTrouveException("l utilisateur avec l id user " + idUser + " est introuvable"));
		logger.debug("Sortie OK de la méthode findUtilisateurById avec l utilisateur : " + utilisateur.toString());
		return utilisateur;
	}

	@Override
	public Utilisateur findUtilisateurByLogin(String login) throws UtilisateurNonTrouveException {
		logger.debug("Entree dans la méthode findUtilisateurByLogin avec le login : " + login);
		Utilisateur utilisateur = this.utilisateurRepository
				.findUtilisateurByLogin(login)
				.orElseThrow(() -> new UtilisateurNonTrouveException("l utilisateur avec le login " + login + " est introuvable"));
		logger.debug("Sortie OK de la méthode findUtilisateurByLogin avec l utilisateur : " + utilisateur.toString());
		return utilisateur;
	}
}