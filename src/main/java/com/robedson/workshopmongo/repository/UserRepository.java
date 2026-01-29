package com.robedson.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robedson.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	/**
	 * UserRepository - acesso aos dados dos users no MongoDB.
	 * Repository do MongoDB para a entidade User.
	 * MongoRepository<User, String> indica que essa interface gerencia documentos do tipo User
	 * Já faz os métodos : findAll, findById, save, deleteById, etc. 
	 * User = entidade/documento
	 * String = tipo do ID
	 */
}
