package br.gov.mt.seplag.api.exception;

public class TokenExpiredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TokenExpiredException(String mensagem) {
		super(mensagem);
	}
	
}
