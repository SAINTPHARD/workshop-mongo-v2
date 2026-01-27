package com.robedson.workshopmongo.dto;

import java.io.Serializable;
import com.robedson.workshopmongo.domain.User; // Importante importar sua classe User

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Data Transfer Object (DTO) para representar o autor dentro de um post.
	 * Projetamos apenas ID e Nome para não duplicar dados desnecessários (como email/senha)
	 * dentro de cada postagem no banco de dados.
	 */
	
	// 1. Atributos
	private String id;
	private String name;

	// 2. Construtor vazio 
	public AuthorDTO() {
	}

	// 3. Construtor "Mágico": Recebe a entidade User e copia os dados
	// Isso facilita muito na hora de instanciar: new AuthorDTO(usuario);
	public AuthorDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
	}

	// 4. Getters e Setters
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
}