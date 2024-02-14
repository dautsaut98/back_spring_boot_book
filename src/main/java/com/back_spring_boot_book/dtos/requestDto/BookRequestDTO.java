package com.back_spring_boot_book.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    @NotNull(message = "l id ne peut etre null")
    private Integer id;

    @NotNull(message = "l id user ne peut etre null")
    private Integer idUser;

    @NotNull(message = "le nom ne peut etre null")
    @Size(min = 3, message = "le nom doit au moins faire 3 caractères")
    private String nom;

    @NotNull(message = "le prenom de l'auteur ne peut etre null")
    @Size(min = 3, message = "le prenom doit au moins faire 3 caractères")
    private String prenomAuteur;

    @NotNull(message = "le nom de l'auteur ne peut etre null")
    @Size(min = 3, message = "le nom de l'auteur doit au moins faire 3 caractères")
    private String nomAuteur;

    @NotNull(message = "la description ne peut etre null")
    @Size(min = 3, message = "la description doit au moins faire 3 caractères")
    private String description;

    @NotNull(message = "la date de description user ne peut etre null")
    private Timestamp dateParution;

    @NotNull(message = "lu ne peut etre null")
    private boolean lu;

    @Override
    public String toString() {
        return "BookRequestDTO [id=" + id + ", nom=" + nom
                + ", prenomAuteur=" + prenomAuteur + ", nomAuteur=" + nomAuteur + ", description=" + description
                + ", dateParution=" + dateParution + ", lu=" + lu + "]";
    }
}