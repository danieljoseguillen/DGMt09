package com.tarea9.dgmt09e11.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea9.dgmt09e11.domain.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
    Boolean existsByCorreo(String correo);
}
