package com.back_spring_boot_book.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
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