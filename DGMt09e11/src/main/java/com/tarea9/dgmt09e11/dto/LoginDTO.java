package com.tarea9.dgmt09e11.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	@NotBlank
	private String nombre;

	@NotBlank
	private String password;
}
