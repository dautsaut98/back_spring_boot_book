package com.back_spring_boot_book.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationRequestDTO {
	@NotNull(message = "le login ne peut etre vide")
	@Size(min = 3, message = "le login doit au moins faire 3 caractères")
	private String login;

	@NotNull(message = "le password ne peut etre vide")
	@Size(min = 3, message = "le password doit au moins faire 3 caractères")
	private String password;
}
