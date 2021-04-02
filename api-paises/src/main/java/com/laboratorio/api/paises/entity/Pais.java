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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="pais")
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoPais;
	
	@Column(nullable=false, length = 50)
	private String nombrePais;
	
	@Column(nullable=false, length = 50)
	private String capitalPais;
	
	@Column(nullable=false, length = 50)
	private String región;
	
	@Column(nullable=false)
	private Long población;
	
	@Column(nullable=false)
	private float latitud;
	
	@Column(nullable=false)
	private float longitud;


}
