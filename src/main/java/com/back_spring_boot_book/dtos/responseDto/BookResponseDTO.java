package com.back_spring_boot_book.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private Integer id;

    private Integer idUser;

    private String nom;

    private String prenomAuteur;

    private String nomAuteur;

    private String description;

    private Timestamp dateParution;

    private boolean lu;

    private String srcImage;

    @Override
    public String toString() {
        return "BookResponseDTO [id=" + id + ", nom=" + nom
                + ", prenomAuteur=" + prenomAuteur + ", nomAuteur=" + nomAuteur + ", description=" + description
                + ", dateParution=" + dateParution + ", lu=" + lu + ", srcImage="+srcImage+"]";
    }
}