package com.back_spring_boot_book.model;

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