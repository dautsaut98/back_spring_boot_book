package com.back_spring_boot_book.exceptions;

public class UtilisateurNonTrouveException extends RuntimeException {

	private static final long serialVersionUID = 6321449314397590117L;

	public UtilisateurNonTrouveException(String message) {
		super(message);
	}
}
