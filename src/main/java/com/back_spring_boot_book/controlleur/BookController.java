package com.back_spring_boot_book.controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_spring_boot_book.converter.ConvertDTOToEntity;
import com.back_spring_boot_book.converter.ConverterEntityToDTO;
import com.back_spring_boot_book.exceptions.BookExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.model.BookDTO;
import com.back_spring_boot_book.service.ServiceBook;
import com.back_spring_boot_book.service.ServiceUtilisateur;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private ServiceBook serviceBook;

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;

    @PostMapping("/book")
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        try {
        	Book book = ConvertDTOToEntity.convertBookDTOToBook(bookDTO);
        	book.setUtilisateur(this.serviceUtilisateur.findUtilisateurById(bookDTO.getIdUser()));
            this.serviceBook.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BookExisteDejaException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch(UtilisateurNonTrouveException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookDTO>> getBookOfUser(@RequestParam Integer idUser) {
        List<BookDTO> booksDTO = this.serviceBook.findByIdUtilisateur(idUser)
        		.stream()
        		.map(ConverterEntityToDTO::convertBookToBookDTO)
        		.peek(book -> book.setIdUser(idUser))
        		.collect(Collectors.toList());
        if (booksDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }
    
    @GetMapping("/book")
    public ResponseEntity<BookDTO> getBookOfUserAndName(@RequestParam Integer idUser, @RequestParam String nameBook) {
        Optional<Book> opBook = this.serviceBook.findBookByNomAndIdUtilisateur(nameBook, idUser);
        if (opBook.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ConverterEntityToDTO.convertBookToBookDTO(opBook.get()));
    }
}
