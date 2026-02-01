package com.robedson.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// 2. Endpoint para buscar usuário por ID
	// GET /users/{id}
	@GetMapping(value = "/{id}") // mapeia requisições do tipo /users/{id}
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
			User user = service.findById(id);
			return ResponseEntity.ok().body(new UserDTO(user));
		}
	
	// CRUD - Create, Read, Update, Delete
	// POST /users : INSERT
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		// 1. Converte DTO para Objeto User
		User obj = service.fromDTO(objDto);
		
		// 2. Insere no banco
		obj = service.insert(obj);
		
		// 3. Cria o cabeçalho com a URL do novo recurso criado (Boas práticas REST)
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		// 4. Retorna código 201 (Created)
		return ResponseEntity.created(uri).build();
	}
	
}

/**
 * Classe UserResource - Controlador REST para gerenciar recursos relacionados aos usuários.
 * @RestController indica que essa classe é um controlador REST, responsável por lidar com requisições HTTP.
 * @RequestMapping(value = "/users") define o endpoint base para acessar os recursos relacionados aos usuários.
 * // @GetMapping: mapeia requisições do tipo /users/{id}
 * 
 * // Converte cada User da lista original para um UserDTO
 * List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
 */