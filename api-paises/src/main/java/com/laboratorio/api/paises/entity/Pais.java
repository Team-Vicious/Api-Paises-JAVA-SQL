package com.laboratorio.api.paises.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="pais")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="pais")
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable=false, length = 10)
	int codigoPais;
	
	@Column(nullable=false, length = 50)
	String nombrePais;
	
	@Column(nullable=false, length = 50)
	String capitalPais;
	
	@Column(nullable=false, length = 50)
	String región;
	
	@Column(nullable=false)
	Long población;
	
	@Column(nullable=false)
	float latitud;
	
	@Column(nullable=false)
	float longitud;


}
