package com.robedson.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robedson.workshopmongo.domain.Post;
import com.robedson.workshopmongo.repository.PostRepository;
import com.robedson.workshopmongo.resources.exception.ObjectNotFoundException;

/**
 * 
 */
@Service // Indicar a classe como servico do spring
public class PostService {

	// Injeçao de dependencia
	@Autowired
	private PostRepository repos;
	
	// Método para buscar um post por ID
	public Post findById(String id) {
		Optional<Post> obj =  repos.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	// Método para buscar todos os posts
	public List<Post> findAll() {
		return repos.findAll();
	}
	
	/**Após a implementação do postrepository, 
	 * vamos  criar o controlador REST que vai receber a requisição GET /posts/{id}.
	 * Na classe PostResource, e im´lementar o método findById, que vai ser chamado pelo controlador
	 */

	// Método findByTitle para buscar posts por título, 
	
	public List<Post> findByTitle(String text) {
		return repos.searchTitleOrdBody(text);
	}
}
