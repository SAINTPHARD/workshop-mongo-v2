package com.robedson.workshopmongo.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * * Data Transfer Object (DTO) para a entidade User.
	 * * DTOs são usados para transferir dados entre diferentes camadas da aplicação,
	 * * como entre o backend e o frontend, sem expor a entidade completa.
	 * 
	 */

	// 1. Atributos
	private String id;
	private String name;
	private String email;

	// 2. Construtor vazio
	public UserDTO() {
	}
	
	// 3. Construtor com argumentos
	public UserDTO(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	/**
	 * Construtor que recebe um objeto User e inicializa o DTO com seus dados.
	 */
	public UserDTO(com.robedson.workshopmongo.domain.User user) {
		this.id = user.getId();
		// ou id = obj.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	// 4. Getters e Setters
	// Permitem acessar e modificar os atributos (dados) privados da classe.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
