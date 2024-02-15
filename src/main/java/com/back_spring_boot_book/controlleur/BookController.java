package com.back_spring_boot_book.controlleur;

import com.back_spring_boot_book.utils.converters.ConvertRequestDTOToEntity;
import com.back_spring_boot_book.utils.converters.ConverterEntityToResponseDTO;
import com.back_spring_boot_book.dtos.requestDto.BookRequestDTO;
import com.back_spring_boot_book.dtos.responseDto.BookResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.back_spring_boot_book.exceptions.BookExisteDejaException;
import com.back_spring_boot_book.exceptions.UtilisateurNonTrouveException;
import com.back_spring_boot_book.model.Book;
import com.back_spring_boot_book.service.serviceImplemente.ServiceBook;
import com.back_spring_boot_book.service.serviceImplemente.ServiceUtilisateur;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private ServiceBook serviceBook;

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/addBook")
    public ResponseEntity<Void> addBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            logger.debug("Appel de addBook avec le livre "+bookRequestDTO.toString());
        	Book book = ConvertRequestDTOToEntity.convertBookDTOToBook(bookRequestDTO);
        	book.setUtilisateur(this.serviceUtilisateur.findUtilisateurById(bookRequestDTO.getIdUser()));
            this.serviceBook.addBook(book);
            logger.debug("retour 200 de addBook");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BookExisteDejaException e) {
            logger.debug("retour 409 de addBook le livre "+bookRequestDTO.getNom()+" existe deja en bdd pour l utlisateur "+bookRequestDTO.getIdUser());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch(UtilisateurNonTrouveException e) {
            logger.debug("retour 404 de addBook, l utilisateur "+bookRequestDTO.getIdUser()+" est introuvable en bdd");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/idUser/{idUser}/all")
    public ResponseEntity<List<BookResponseDTO>> getBookOfUser(@Valid @PathVariable Integer idUser) {
        logger.debug("Appel de getBookOfUser avec l id user "+idUser);
        List<BookResponseDTO> booksDTO = this.serviceBook.findByIdUtilisateur(idUser)
        		.stream()
        		.map(ConverterEntityToResponseDTO::convertBookToBookDTO)
        		.peek(book -> book.setIdUser(idUser))
        		.collect(Collectors.toList());
        if (booksDTO.isEmpty()) {
            logger.debug("retour 404 de getBookOfUser, la liste de livre pour l utilisateur "+idUser+" est introuvable en bdd");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.debug("retour 200 de getBookOfUser avec pour l utiilisateur "+idUser+" avec la liste de livre : "+booksDTO);
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }

    @GetMapping("/idUser/{idUser}/name/{nameBook}")
    public ResponseEntity<BookResponseDTO> getBookOfUserAndName(@Valid @PathVariable Integer idUser, @PathVariable String nameBook) {
        logger.debug("Appel de getBookOfUserAndName avec l id user "+idUser+" et le livre "+nameBook);
        Optional<Book> opBook = this.serviceBook.findBookByNomAndIdUtilisateur(nameBook, idUser);
        if (opBook.isEmpty()) {
            logger.debug("retour 404 de getBookOfUserAndName, le livre "+nameBook+" pour l utilisateur "+idUser+" est introuvable en bdd");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.debug("retour 200 de getBookOfUserAndName avec pour l utilisateur "+idUser+" avec le livre : "+opBook.get());
        return ResponseEntity.status(HttpStatus.OK).body(ConverterEntityToResponseDTO.convertBookToBookDTO(opBook.get()));
    }
}
