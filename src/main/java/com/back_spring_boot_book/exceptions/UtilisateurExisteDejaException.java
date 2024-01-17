package com.back_spring_boot_book.exceptions;

public class UtilisateurExisteDejaException extends RuntimeException {

	private static final long serialVersionUID = -4227655166368475375L;

	public UtilisateurExisteDejaException(String message) {
		super(message);
	}
}
