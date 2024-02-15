package com.back_spring_boot_book.service.serviceInterface;

import com.back_spring_boot_book.exceptions.UtilisateurExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserServiceDetails {
    /**
     * Permet de récupérer un utilisateur en bdd si son login et password son bons et le transform en UserDetails
     * @param username
     * @param password
     * @return l'utilisateur si trouvé
     * @throws UtilisateurNonTrouveException si l'utilisateur non trové en bdd
     */
    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UtilisateurNonTrouveException;

    /**
     * Ajoute un utilisateur en BDD.
     * @param utilisateur
     * @return l'utilisateur rajouté
     * @throws UtilisateurExisteDejaException
     */
    public UserDetails saveUser(Utilisateur utilisateur) throws UtilisateurExisteDejaException;

    /**
     * Trouve un utilisateur par login
     * @param login
     * @return l'utilisateur trouvé
     */
    public Optional<Utilisateur> findUtilisateurByLogin(String login);

    /**
     * Permet de récupérer un utilisateur en bdd si son login est bon et le transform en UserDetails
     * @param username
     * @return l'utilisateur si trouvé
     * @throws UtilisateurNonTrouveException si l'utilisateur non trové en bdd
     */
    public UserDetails loadUserByUsername(String username) throws UtilisateurNonTrouveException;
}
