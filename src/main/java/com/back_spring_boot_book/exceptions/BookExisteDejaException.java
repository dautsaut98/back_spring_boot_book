package com.back_spring_boot_book.exceptions;

public class BookExisteDejaException extends RuntimeException {

	private static final long serialVersionUID = 8626688928381159422L;

	public BookExisteDejaException(String message) {
		super(message);
	}
}
