package com.laboratorio.api.paises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class ApiPaisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPaisesApplication.class, args);
		
		ConexionPaises conexionPaises = new ConexionPaises();
		try {
			conexionPaises.ingresarPaises();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}