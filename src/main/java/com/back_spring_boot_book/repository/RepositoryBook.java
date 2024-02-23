package com.back_spring_boot_book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back_spring_boot_book.model.Book;

@Repository
public interface RepositoryBook extends JpaRepository<Book, Integer>{

	Book save(Book book);

	Optional<List<Book>> findAllByUtilisateurId(Integer idUtilisateur);

	Optional<Book> findByNomAndUtilisateurId(String nom, Integer idUtilisateur);

	Optional<Book> findByIdAndUtilisateurId(Integer idBook, Integer idUtilisateur);
}