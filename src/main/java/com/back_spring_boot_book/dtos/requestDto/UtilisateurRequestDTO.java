package com.back_spring_boot_book.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurRequestDTO {
    @NotNull(message = "l'id ne peut etre null")
    private Integer id;

    @NotNull(message = "le login ne peut etre null")
    @Size(min = 3, message = "le login doit au moins faire 3 caractères")
    private String login;

    @NotNull(message = "le password ne peut etre null")
    @Size(min = 3, message = "le password doit au moins faire 3 caractères")
    private String password;

    @NotNull(message = "le prenom ne peut etre null")
    @Size(min = 3, message = "le prenom doit au moins faire 3 caractères")
    private String prenom;

    @NotNull(message = "le nom ne peut etre null")
    @Size(min = 3, message = "le nom doit au moins faire 3 caractères")
    private String nom;

    @NotNull(message = "l'email ne peut etre null")
    @Email(message = "l'email n'est pas conforme")
    private String email;

    @Override
    public String toString() {
        return "UtilisateurRequestDTO [id=" + id + ", login=" + login + ", prenom=" + prenom + ", nom="
                + nom + ", email=" + email + "]";
    }
}