package com.robedson.workshopmongo.resources.exception;

public class StandardError {

	// Atributos para tratamento de erros
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	// Construtor vazio
	public StandardError() {
		
	}
	
	// construtor com parametros (padrão)
	public StandardError(Long timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
}
