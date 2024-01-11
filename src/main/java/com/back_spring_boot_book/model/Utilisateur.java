package com.back_spring_boot_book.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String login;

	private String password;

	private String prenom;

	private String nom;

	private String email;

	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books;

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + ", prenom=" + prenom + ", nom="
				+ nom + ", email=" + email + "]";
	}
}