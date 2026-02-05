package com.robedson.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.robedson.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	/**
	 * PostRepository - acesso aos dados dos posts no MongoDB.
	 * Interface de repositório para gerenciar (ex: Save, Delete, Update) operações relacionadas aos posts no banco de dados.
	 * Repositório para gerenciar operações relacionadas aos posts no banco de dados.
	 */
 // Query Method: O Spring monta a busca 'LIKE %texto%' ignorando maiúsculas/minúsculas
 List<Post> findByTitleContainingIgnoreCase(String text);
}
