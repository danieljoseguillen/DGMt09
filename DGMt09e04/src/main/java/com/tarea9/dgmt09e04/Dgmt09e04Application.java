package com.tarea9.dgmt09e04;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tarea9.dgmt09e04.domain.Departamento;
import com.tarea9.dgmt09e04.domain.Empleado;
import com.tarea9.dgmt09e04.modelos.Estado;
import com.tarea9.dgmt09e04.modelos.Genero;
import com.tarea9.dgmt09e04.servicios.dService;
import com.tarea9.dgmt09e04.servicios.eService;

@SpringBootApplication
public class Dgmt09e04Application {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt09e04Application.class, args);
	}

		@Bean
	CommandLineRunner initData(eService eServ,dService dServ){
		return args -> {
			Departamento depto1= dServ.agregar(new Departamento(null,"Informatica",null));
			Departamento depto2= dServ.agregar(new Departamento(null,"RR.HH",null));
			Departamento depto3= dServ.agregar(new Departamento(null,"Logistica",null));
			eServ.agregar(new Empleado(null,"Jose","Jose@gmail.com",30000d,Estado.ACTIVO,Genero.MASCULINO,depto1));
			eServ.agregar(new Empleado(null,"Pedro","pedrog@gmail.com",32000d,Estado.ACTIVO,Genero.MASCULINO,depto2));
			eServ.agregar(new Empleado(null,"Maria","mcarolina@gmail.com",35000d,Estado.INACTIVO,Genero.FEMENINO,depto3));
		};
	}
}
