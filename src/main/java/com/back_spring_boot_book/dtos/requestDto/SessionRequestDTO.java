package com.back_spring_boot_book.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequestDTO {
    @NotNull(message = "l'id ne peut etre null")
    private Integer id;

    @NotNull(message = "la date ne peut etre null")
    private Timestamp date;

    @NotNull(message = "la duree ne peut etre null")
    @Size(min = 1, message = "la duree n'est pas conforme")
    private Integer duree;

    @NotNull(message = "le nombre de page lu ne peut etre null")
    @Size(min = 1, message = "le nombre de page lu n'est pas conforme")
    private Integer nombreDePageLu;

    @NotNull(message = "l'id du livre ne peut etre null")
    private Integer idlivre;

    @Override
    public String toString() {
        return "SessionRequestDTO [id=" + id + ", date=" + date + ", duree=" + duree + ", nombreDePageLu=" + nombreDePageLu+"]";
    }
}
