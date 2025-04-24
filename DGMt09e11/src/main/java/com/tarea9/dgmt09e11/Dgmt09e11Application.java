package com.tarea9.dgmt09e11;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tarea9.dgmt09e11.Repositorios.UsuarioRepository;
import com.tarea9.dgmt09e11.domain.Empleado;
import com.tarea9.dgmt09e11.domain.Usuario;
import com.tarea9.dgmt09e11.modelos.Estado;
import com.tarea9.dgmt09e11.modelos.Genero;
import com.tarea9.dgmt09e11.modelos.Rol;
import com.tarea9.dgmt09e11.servicios.eService;

@SpringBootApplication
public class Dgmt09e11Application {
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(Dgmt09e11Application.class, args);
	}

	@Bean
	CommandLineRunner initData(eService servicio, UsuarioRepository uRep) {
		return args -> {
			Usuario admin = new Usuario(null, "Administrador",
					"admin@example.com",
					encoder.encode("1234"),
					Rol.valueOf("ADMIN"), new ArrayList<>());
			Usuario user = new Usuario(null, "Usuario",
					"user@example.com",
					encoder.encode("1234"),
					Rol.valueOf("USER"), new ArrayList<>());
			uRep.save(admin);
			uRep.save(user);
			servicio.agregar(
					new Empleado(null, "Jose", "Jose@gmail.com", 30000d, Estado.ACTIVO, Genero.MASCULINO, admin));
			servicio.agregar(
					new Empleado(null, "Pedro", "pedrog@gmail.com", 32000d, Estado.ACTIVO, Genero.MASCULINO, admin));
			servicio.agregar(
					new Empleado(null, "Maria", "mcarolina@gmail.com", 35000d, Estado.INACTIVO, Genero.FEMENINO, admin));
			servicio.agregar(
					new Empleado(null, "Miguel", "mg129@gmail.com", 30000d, Estado.ACTIVO, Genero.MASCULINO, user));
		};
	}
}
