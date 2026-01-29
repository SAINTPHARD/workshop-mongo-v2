package com.robedson.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robedson.workshopmongo.domain.User;
import com.robedson.workshopmongo.dto.UserDTO;
import com.robedson.workshopmongo.services.UserService;

/**
 * Controlador REST para gerenciar recursos relacionados aos usuários.
 * @RestController indica que essa classe é um controlador REST, responsável por lidar com requisições HTTP.
 * @RequestMapping(value = "/users") define o endpoint base para acessar os recursos relacionados aos usuários.
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {	

	// Injeção de dependência do UserService
	@Autowired
	private UserService service;
	
	// ======================================
	// 1. Endpoint para buscar todos os usuários
	// ======================================
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		
		// Converte cada User da lista original para um UserDTO
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
    
}
