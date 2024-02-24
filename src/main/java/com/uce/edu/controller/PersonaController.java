package com.uce.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.DeleteExchange;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.uce.edu.repository.modelo.Persona;
import com.uce.edu.service.IPersonaService;
// la capa solo tiene una clase 

// el controlador es quien debe controlar la solicitud




@Controller
//este es el controlador para la peticion
//http://localhost:8085/personas/buscarTodos
//http://localhost:8080/personas/actualizar
//http://localhost:8080/personas/borrar
//http://localhost:8080/personas/guardar
// anivel de controlador , se controla  de recursos
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	private IPersonaService personaService;
	
	//diferentes tipos de request
	//se los conoce como verbos o metodos HTTP
	
	//path
	//GET
	@GetMapping("/buscarTodos")
	public String buscarPorCedula(Model modelo) {
		List<Persona> lista = this.personaService.consultarTodos();
		modelo.addAttribute("personas",lista);
		return "vistaListaPersonas";
	}
	//PUT
	@PutMapping("/actualizar")
	public String actualizar() {
		return "";
	}
	@DeleteMapping("/borrar")
	public String borrar() {
		return "";
	}
	@PostMapping("/guardar")
	public String guardar() {
		return "";
	}
}
