package com.back_spring_boot_book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO {
	private Integer id;

	private String login;

	private String password;

	private String prenom;

	private String nom;

	private String email;

	@Override
	public String toString() {
		return "UtilisateurDTO [id=" + id + ", login=" + login + ", password=" + password + ", prenom=" + prenom + ", nom="
				+ nom + ", email=" + email + "]";
	}
}