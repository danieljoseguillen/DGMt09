package com.tarea9.dgmt09e11.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tarea9.dgmt09e11.modelos.Rol;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre"),
    @UniqueConstraint(columnNames = "correo")
})
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String nombre;

  @NotBlank
  @Size(max = 50)
  @Email
  private String correo;

  @NotBlank
  private String password;

  private Rol rol;

  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "creador",cascade = CascadeType.ALL)
  private List<Empleado> empleados = new ArrayList<>();
}