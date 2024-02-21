package com.back_spring_boot_book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back_spring_boot_book.model.Utilisateur;

@Repository
public interface RepositoryUtilisateur extends JpaRepository<Utilisateur, Integer>{

	Optional<Utilisateur> findUtilisateurByLoginAndPassword(String login, String password);

	Optional<Utilisateur> findUtilisateurByLogin(String login);

	Optional<Utilisateur> findUtilisateurByLoginOrEmail(String login, String email);

	Utilisateur save(Utilisateur newUtilisateur);
}