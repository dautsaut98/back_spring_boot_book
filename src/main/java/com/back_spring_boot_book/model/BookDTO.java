package com.back_spring_boot_book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	private Integer id;

	private Integer idUser;

	private String nom;

	private String prenomAuteur;

	private String nomAuteur;

	private String description;

	private String dateParution;

	private boolean lu;

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", nom=" + nom
				+ ", prenomAuteur=" + prenomAuteur + ", nomAuteur=" + nomAuteur + ", description=" + description
				+ ", dateParution=" + dateParution + ", lu=" + lu + "]";
	}
}