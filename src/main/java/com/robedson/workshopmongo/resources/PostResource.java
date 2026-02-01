package com.robedson.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robedson.workshopmongo.domain.Post;
import com.robedson.workshopmongo.services.PostService;

@RestController						// define a classe como um controlador
@RequestMapping(value = "/posts")	// Define o caminho padrão como /posts
public class PostResource {

	// Injeção de dependencias
	@Autowired 
	private PostService service;
	
	// Endpoint para buscar por ID: http://localhost:8080/posts/1
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = (Post) service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	// Endpoint para buscar todos os posts: http://localhost:8080/posts
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post>list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
