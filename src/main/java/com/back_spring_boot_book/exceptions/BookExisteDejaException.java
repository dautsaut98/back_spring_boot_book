package com.back_spring_boot_book.exceptions;

public class BookExisteDejaException extends RuntimeException {

	public BookExisteDejaException(String message) {
		super(message);
	}
}
