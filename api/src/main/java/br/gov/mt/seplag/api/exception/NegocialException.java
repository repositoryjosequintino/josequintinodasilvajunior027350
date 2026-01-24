package br.gov.mt.seplag.api.exception;

public class NegocialException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NegocialException(String mensagem) {
		super(mensagem);
	}
	
}
