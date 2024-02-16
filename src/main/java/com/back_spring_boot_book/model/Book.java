package com.back_spring_boot_book.model;

import java.sql.Timestamp;
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
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Session> sessions;

	@Column(name = "nom", nullable = false, length = 30)
	private String nom;

	@Column(name = "prenomAuteur", nullable = false, length = 30)
	private String prenomAuteur;

	@Column(name = "nomAuteur", nullable = false, length = 30)
	private String nomAuteur;

	@Column(name = "description", nullable = false, length = 30)
	private String description;

	@Column(name = "dateParution", nullable = false, length = 10)
	private Timestamp dateParution;

	@Column(name = "lu", nullable = false)
	private boolean lu;

	@Column(name = "srcImage", nullable = false)
	private String srcImage;

	@Override
	public String toString() {
		return "Book [id=" + id + ", utilisateur=" + utilisateur + ", sessions=" + sessions + ", nom=" + nom
				+ ", prenomAuteur=" + prenomAuteur + ", nomAuteur=" + nomAuteur + ", description=" + description
				+ ", dateParution=" + dateParution + ", lu=" + lu + ", srcImage="+srcImage+"]";
	}
}