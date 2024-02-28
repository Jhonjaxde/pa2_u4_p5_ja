package com.uce.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
// a nivel de controlador , se controla  de recursos
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	private IPersonaService personaService;
	
	//diferentes tipos de request
	//se los conoce como verbos o metodos HTTP
	
	//path
	//GET
	@GetMapping("/buscarTodos")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.personaService.consultarTodos();
		modelo.addAttribute("personas",lista);
		return "vistaListaPersonas";
	}
	
	
	//GET 
	//http://localhost:8085/personas/buscarPorCedula/1720368248
	///buscarPorCedula/1
	@GetMapping("/buscarPorCedula/{cedulaPersona}")
	public String buscarPorCedula(@PathVariable("cedulaPersona") String cedula,Model modelo) {
		Persona persona= this.personaService.consultarPorCedula(cedula);
		modelo.addAttribute("persona",persona);
		return "vistaPersona";
	}
	//PUT
	@PutMapping("/actualizar/{cedulaPersona}")
	public String actualizar(@PathVariable("cedulaPersona") String cedula,Persona persona) {
		//persona.setCedula(cedula);
		Persona aux = this.personaService.consultarPorCedula(cedula);
		aux.setApellido(persona.getApellido());
		aux.setCedula(persona.getCedula());
		aux.setGenero(persona.getGenero());
		aux.setNombre(persona.getNombre());
		this.personaService.actualizar(persona);
		return "redirect:/personas/buscarTodos";
	}
	@DeleteMapping("/borrar/{cedula}")
	public String borrar(@PathVariable("cedula") String cedula) {
		this.personaService.borrarPorCedula(cedula);
		return "redirect:/personas/buscarTodos";
	}
	@PostMapping("/guardar")
	public String guardar(Persona persona) {
		this.personaService.guardar(persona);
		return "redirect:/personas/buscarTodos";
	}
	
	@GetMapping("/nuevaPersona")
	public String mostrarNuevaPersona(Model modelo) {
		modelo.addAttribute("persona", new Persona());
		return "vistaNuevaPersona";
		
	}
	
	
}
