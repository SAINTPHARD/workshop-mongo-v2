package com.robedson.workshopmongo.services.exception;

/*
 * Exceção personalizada para indicar que um objeto não foi encontrado no banco de dados.
 * Estende RuntimeException, que é uma exceção não verificada.
 * Exemplo: quando um usuário com um ID específico não existe.
 */
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
