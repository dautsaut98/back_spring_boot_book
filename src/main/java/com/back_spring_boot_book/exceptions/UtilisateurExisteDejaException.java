package com.back_spring_boot_book.exceptions;

public class UtilisateurExisteDejaException extends RuntimeException {

	public UtilisateurExisteDejaException(String message) {
		super(message);
	}
}
