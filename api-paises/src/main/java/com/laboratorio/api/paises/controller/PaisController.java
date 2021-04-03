package com.laboratorio.api.paises.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.microservicios.generics.controllers.ControllerImpl;
import com.formaciondbi.microservicios.generics.services.ServicesImpl;
import com.laboratorio.api.paises.entity.Pais;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/pais")
public class PaisController extends ControllerImpl<Pais, ServicesImpl<Pais,Long>>{

}
