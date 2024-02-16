package com.back_spring_boot_book.service.serviceImplemente;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.repository.RepositoryUtilisateur;
import com.back_spring_boot_book.service.serviceInterface.IUserServiceDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceDetailsImpl implements IUserServiceDetails {

    @Autowired
    private RepositoryUtilisateur repositoryUtilisateur;

    Logger logger = LoggerFactory.getLogger(UserServiceDetailsImpl.class);

    @Override
    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UtilisateurNonTrouveException {
        logger.debug("Entree dans la méthode loadUserByUsernameAndPassword pour rechercher de l'utilisateur par login et password en bdd, login : "+username);
        // Recherche de l'utilisateur dans la base de données par son nom d'utilisateur
        Utilisateur personne = repositoryUtilisateur
                .findUtilisateurByLoginAndPassword(username, password)
                .orElseThrow(() -> new UtilisateurNonTrouveException("login ou mot de passe incorrect"));

        logger.debug("Sortie OK de la méthode loadUserByUsernameAndPassword avec l utilisateur : "+username);
        // Création d'un objet UserDetails à partir des informations de l'utilisateur trouvé
        return new User(
                personne.getLogin(), // Nom d'utilisateur
                personne.getPassword(), // Mot de passe
                new ArrayList<>() // Autorisations (rôles) de l'utilisateur (vide ici)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UtilisateurNonTrouveException {
        logger.debug("Entree dans la méthode loadUserByUsername pour rechercher de l'utilisateur par login et password en bdd, login : "+username);
        // Recherche de l'utilisateur dans la base de données par son nom d'utilisateur
        Utilisateur personne = repositoryUtilisateur
                .findUtilisateurByLogin(username)
                .orElseThrow(() -> new UtilisateurNonTrouveException("login ou mot de passe incorrect"));

        logger.debug("Sortie OK de la méthode loadUserByUsername avec l utilisateur : "+username);
        // Création d'un objet UserDetails à partir des informations de l'utilisateur trouvé
        return new User(
                personne.getLogin(), // Nom d'utilisateur
                personne.getPassword(), // Mot de passe
                new ArrayList<>() // Autorisations (rôles) de l'utilisateur (vide ici)
        );
    }

    @Override
    public UserDetails saveUser(Utilisateur utilisateur) throws UtilisateurExisteDejaException {
        logger.debug("Entree dans la méthode saveUser avec l'utilisateur : "+utilisateur.toString());
        this.findUtilisateurByLogin(utilisateur.getLogin())
                .ifPresent(user -> {throw new UtilisateurExisteDejaException("l'utilisateur avec le login " + utilisateur.getLogin() + " existe déjà");});
        Utilisateur utilisateurSave = this.repositoryUtilisateur.save(utilisateur);
        logger.debug("Sortie OK de la méthode saveUser avec l utilisateur : "+utilisateurSave.toString());
        return new User(
                utilisateurSave.getEmail(), // Nom d'utilisateur
                utilisateurSave.getPassword(), // Mot de passe
                new ArrayList<>() // Autorisations (rôles) de l'utilisateur (vide ici)
        );
    }

    @Override
    public Optional<Utilisateur> findUtilisateurByLogin(String login) {
        logger.debug("Entree dans la méthode findUtilisateurByLogin avec le login : "+login);
        Optional<Utilisateur> opUtilisateur = this.repositoryUtilisateur.findUtilisateurByLogin(login);
        if(opUtilisateur.isEmpty())
            logger.debug("Sortie OK de la méthode findUtilisateurByLogin avec l utilisateur" + login + "absent");
        else
            logger.debug("Sortie OK de la méthode findUtilisateurByLogin pour l utilisateur avec le login "+login+" trouvé est : "+ opUtilisateur.get());
        return opUtilisateur;
    }
}