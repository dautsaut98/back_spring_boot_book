package com.back_spring_boot_book.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "date", nullable = false)
	private Timestamp date;

	@Column(name = "duree", nullable = false)
	private String duree;

	@Column(name = "nombreDePageLu", nullable = false)
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