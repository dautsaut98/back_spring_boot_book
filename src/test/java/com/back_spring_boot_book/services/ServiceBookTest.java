package com.back_spring_boot_book.services;

import com.back_spring_boot_book.exceptions.BookExisteDejaException;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.Utilisateur;
import com.back_spring_boot_book.repository.RepositoryBook;
import com.back_spring_boot_book.service.serviceImplemente.ServiceBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceBookTest {

    @InjectMocks
    ServiceBook serviceBook;

    @Mock
    RepositoryBook repositoryBook;

    @Test()
    public void testAddBookWithBookExisteDejaException(){
        // GIVEN
        Book bookWantSave = Book.builder()
                .nom("livre 1")
                .lu(false)
                .id(1)
                .description("je suis une description")
                .prenomAuteur("prenom Auteur")
                .nomAuteur("nom auteur")
                .utilisateur(Utilisateur.builder().id(1).build())
                .build();
        Mockito.when(repositoryBook.findByNomAndUtilisateurId(Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(Optional.of(bookWantSave));

        // WHEN et THEN
        Assertions.assertThrows(BookExisteDejaException.class,() -> this.serviceBook.addBook(bookWantSave));
    }

    @Test()
    public void testAddBookOK(){
        // GIVEN
        Book bookWantSave = Book.builder()
                .nom("livre 1")
                .lu(false)
                .id(1)
                .description("je suis une description")
                .prenomAuteur("prenom Auteur")
                .nomAuteur("nom auteur")
                .utilisateur(Utilisateur.builder().id(1).build())
                .build();
        Mockito.when(repositoryBook.findByNomAndUtilisateurId(Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(Optional.empty());
        Mockito.when(repositoryBook.save(Mockito.any(Book.class))).thenReturn(bookWantSave);

        // WHEN and THEN
        this.serviceBook.addBook(bookWantSave);
    }

    @Test
    public void testFindByIdUtilisateurWithNoBookFind(){
        // GIVEN
        Mockito.when(this.repositoryBook.findAllByUtilisateurId(Mockito.any(Integer.class))).thenReturn(Optional.empty());

        // WHEN
        List<Book> bookList = this.serviceBook.findByIdUtilisateur(0);

        // THEN
        Assertions.assertEquals(0, bookList.size());
    }

    @Test
    public void testFindByIdUtilisateurOk(){
        // GIVEN
        List<Book> bookListReturnRepository = Arrays.asList(
                Book.builder()
                        .id(1)
                        .nom("livre1")
                        .prenomAuteur("prenomAuteur1")
                        .nomAuteur("nomAuteur1")
                        .build(),
                Book.builder()
                        .id(2)
                        .nom("livre2")
                        .prenomAuteur("prenomAuteur2")
                        .nomAuteur("nomAuteur2")
                        .build()
        );
        Mockito.when(this.repositoryBook.findAllByUtilisateurId(Mockito.any(Integer.class))).thenReturn(Optional.of(bookListReturnRepository));

        // WHEN
        List<Book> bookList = this.serviceBook.findByIdUtilisateur(0);

        // THEN
        Assertions.assertEquals(2, bookList.size());
        Assertions.assertEquals(bookListReturnRepository.get(0), bookList.get(0));
        Assertions.assertEquals(bookListReturnRepository.get(1), bookList.get(1));
    }

    @Test
    public void findBookByNomAndIdUtilisateur() {
        Book bookRepositoryReturn = Book.builder()
                .nom("livre 1")
                .lu(false)
                .id(1)
                .description("je suis une description")
                .prenomAuteur("prenom Auteur")
                .nomAuteur("nom auteur")
                .utilisateur(Utilisateur.builder().id(1).build())
                .build();
        Mockito.when(this.repositoryBook.findByNomAndUtilisateurId(Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(Optional.of(bookRepositoryReturn));

        // WHEN
        Optional<Book> optionalBookReturn = this.serviceBook.findBookByNomAndIdUtilisateur("livre 1", 0);

        // THEN
        Assertions.assertTrue(optionalBookReturn.isPresent());
        Assertions.assertEquals(bookRepositoryReturn, optionalBookReturn.get());
    }
}
