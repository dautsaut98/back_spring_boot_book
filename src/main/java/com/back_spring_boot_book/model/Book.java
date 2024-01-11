package com.back_spring_boot_book.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Session> sessions;

	private String nom;

	private String prenomAuteur;

	private String nomAuteur;

	private String description;

	private String dateParution;

	private boolean lu;

	@Override
	public String toString() {
		return "Book [id=" + id + ", utilisateur=" + utilisateur + ", sessions=" + sessions + ", nom=" + nom
				+ ", prenomAuteur=" + prenomAuteur + ", nomAuteur=" + nomAuteur + ", description=" + description
				+ ", dateParution=" + dateParution + ", lu=" + lu + "]";
	}
}