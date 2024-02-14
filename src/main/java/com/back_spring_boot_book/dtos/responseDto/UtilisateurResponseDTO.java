package com.back_spring_boot_book.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurResponseDTO {
    private Integer id;

    private String login;

    private String prenom;

    private String nom;

    private String email;

    @Override
    public String toString() {
        return "UtilisateurResponseDTO [id=" + id + ", login=" + login + ", prenom=" + prenom + ", nom="
                + nom + ", email=" + email + "]";
    }
}