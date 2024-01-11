package com.back_spring_boot_book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String date;

	private String duree;

	private Integer nombreDePageLu;

	@ManyToOne
	@JoinColumn(name = "livre_id")
	private Book livre;

	@Override
	public String toString() {
		return "Session [id=" + id + ", date=" + date + ", duree=" + duree + ", nombreDePageLu=" + nombreDePageLu
				+ ", livre=" + livre + "]";
	}
}