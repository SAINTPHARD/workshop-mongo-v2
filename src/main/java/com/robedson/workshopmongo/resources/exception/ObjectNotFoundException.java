package com.robedson.workshopmongo.resources.exception;
/**
 * Garantir a Exceção Personalizada para objeto não encontrado.
 * @param msg Mensagem de erro que descreve a exceção.
 */
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	// Construtor da classe ObjectNotFoundException.
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
