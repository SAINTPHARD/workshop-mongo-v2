package com.robedson.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * @Document indica que essa classe será mapeada como um documento do MongoDB.
 * O "collection = user" define o nome da collection no banco.
 * classe User - representa um documento Mongo (users)
 */

@Document(collection = "User")	// Define o nome da coleção no MongoDB.
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Serializable - Padrão JAVA permitir conversão de objetos 
	 * em bytes para enviar pela rede ou salvar em arquivos.
	 * @serializable

	 */
	
	
	// 1. Atributos
	@Id 				// Indica o campo que será o identificador principal do documento no MongoDB.
	private String id;
	private String name;
	private String email;

	// ASSOCIAÇÃO COM POSTS
	/**
	 * @DBRef(lazy = true) Indica que os posts não serão salvos "embutidos" aqui dentro.
	 * O Spring SÓ vai buscar os posts no banco de dados se chamarmos o getPosts().
	 * new ArrayList<>(); - Instancia a lista vazia para evitar NullPointerException
	 */
	@DBRef(lazy = true) // 
	private List<Post> posts = new ArrayList<>(); // Instancia a lista vazia para evitar NullPointerException

	// 2. Construtor vazio
	public User() {
		super();
	}

	// 3. Construtor com argumentos
	// Util para usuario ja instanciado
	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	// 4. Getters e Setters: permitem acessar e modificar os atributos (dados) privados da classe.
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
	
	// Getters e Setters do Posts
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	/**
	 * 5. hashCode e equals
	 * Permitem comparar objetos de forma adequada (por exemplo, em testes).
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof User)) return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
