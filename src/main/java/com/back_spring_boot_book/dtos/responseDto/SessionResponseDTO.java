package com.back_spring_boot_book.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponseDTO {
    private Integer id;

    private Timestamp date;

    private String duree;

    private Integer nombreDePageLu;

    private Integer idlivre;

    @Override
    public String toString() {
        return "SessionResponseDTO [id=" + id + ", date=" + date + ", duree=" + duree + ", nombreDePageLu=" + nombreDePageLu+"]";
    }
}
