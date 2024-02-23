package com.back_spring_boot_book.exceptions;

public class BookNonTrouveException extends RuntimeException {

    public BookNonTrouveException(String message) {
        super(message);
    }
}
