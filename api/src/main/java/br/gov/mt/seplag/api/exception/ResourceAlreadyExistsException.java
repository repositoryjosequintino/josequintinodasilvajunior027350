package br.gov.mt.seplag.api.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String mensagem) {
		super(mensagem);
	}
	
}
