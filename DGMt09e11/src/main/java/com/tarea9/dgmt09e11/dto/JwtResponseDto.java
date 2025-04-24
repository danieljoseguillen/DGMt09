package com.tarea9.dgmt09e11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class JwtResponseDto {
  private String accessToken;
  private String tokenType;     // "Bearer"
  private Long id;
  private String nombre;
  private String correo;
  private String rol;
}
