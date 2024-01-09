package com.back_spring_boot_book.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.repository.RepositoryUtilisateur;

@Service
public class ServiceUtilisateur implements IServiceUtilisateur{

	// Repository des utilisateurs.
	private final RepositoryUtilisateur utilisateurRepository;

	Logger logger = LoggerFactory.getLogger(ServiceUtilisateur.class);

	public ServiceUtilisateur(RepositoryUtilisateur utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws UtilisateurExisteDejaException {
		if(this.findUtilisateurByLogin(utilisateur.getLogin()).isPresent()) {
			logger.error("l'utilisateur avec le login "+utilisateur.getLogin()+" existe dejà");
			throw new UtilisateurExisteDejaException();
		}
		Utilisateur utilisateurSave = this.utilisateurRepository.save(utilisateur);
		logger.info("l'utilisateur avec le login "+utilisateurSave.getLogin()+" est enregistre");
		return utilisateurSave;
	}

	@Override
	public Utilisateur findUtilisateurByLoginAndPassword(String login, String password) throws UtilisateurNonTrouveException {
		Optional<Utilisateur> opUtilisateur = this.utilisateurRepository.findUtilisateurByLoginAndPassword(login, password);
		logger.info("si l utilisateur avec le login "+login+" et le password "+password+" est trouvé : "+opUtilisateur.isPresent());
		opUtilisateur.ifPresent(utilisateurFind -> logger.info("l utilisateur avec le login "+login+" trouvé est : "+utilisateurFind.toString()));
		return opUtilisateur.orElseThrow(() -> new UtilisateurNonTrouveException());
	}

	@Override
	public Optional<Utilisateur> findUtilisateurByLogin(String login) {
		Optional<Utilisateur> opUtilisateur = this.utilisateurRepository.findUtilisateurByLogin(login);
		logger.info("si l utilisateur avec le login "+login+" est trouvé : "+opUtilisateur.isPresent());
		opUtilisateur.ifPresent(utilisateurFind -> logger.info("l utilisateur avec le login "+login+" trouvé est : "+utilisateurFind.toString()));
		return opUtilisateur;
	}

	@Override
	public Utilisateur findUtilisateurById(Integer idUser) throws UtilisateurNonTrouveException {
		return this.utilisateurRepository.findById(idUser).orElseThrow(() -> new UtilisateurNonTrouveException());
	}
}