package br.gov.mt.seplag.api.exception;

public class InvalidCredentialException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidCredentialException(String mensagem) {
		super(mensagem);
	}
	
}
