package com.back_spring_boot_book.exceptions;

public class DonneeRequestNonConformeException extends RuntimeException {
    public DonneeRequestNonConformeException(String message) {
        super(message);
    }
}
