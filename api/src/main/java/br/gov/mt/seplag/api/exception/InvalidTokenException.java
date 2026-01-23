package br.gov.mt.seplag.api.exception;

public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidTokenException(String mensagem) {
		super(mensagem);
	}
	
}
