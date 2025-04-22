package com.tarea9.dgmt09e11.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	@NotBlank
	private String nombre;

	@NotBlank
	private String password;
}
