package com.back_spring_boot_book.model;

import java.util.List;

import javax.persistence.*;

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

	@Column(name = "login", nullable = false, length = 30)
	private String login;

	@Column(name = "password", nullable = false, length = 30)
	private String password;

	@Column(name = "prenom", nullable = false, length = 30)
	private String prenom;

	@Column(name = "nom", nullable = false, length = 30)
	private String nom;

	@Column(name = "email", nullable = false, length = 30)
	private String email;

	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books;

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", prenom=" + prenom + ", nom="
				+ nom + ", email=" + email + "]";
	}
}