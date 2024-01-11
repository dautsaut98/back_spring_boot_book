package com.back_spring_boot_book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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