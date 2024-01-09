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
public class SessionDTO {
	private Integer id;

	private String date;

	private String duree;

	private Integer nombreDePageLu;

	private Integer idlivre;

	@Override
	public String toString() {
		return "SessionDTO [id=" + id + ", date=" + date + ", duree=" + duree + ", nombreDePageLu=" + nombreDePageLu+"]";
	}
}