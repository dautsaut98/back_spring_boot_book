package com.back_spring_boot_book.exceptions;

public class BookNonSupprimeException extends RuntimeException {

    public BookNonSupprimeException(String message) {
        super(message);
    }
}
