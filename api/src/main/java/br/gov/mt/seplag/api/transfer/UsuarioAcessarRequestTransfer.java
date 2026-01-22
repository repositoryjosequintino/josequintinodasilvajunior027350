package br.gov.mt.seplag.api.transfer;

import jakarta.validation.constraints.NotBlank;

public class UsuarioAcessarRequestTransfer {
	
	@NotBlank(message = "Campo de preenchimento obrigatório!")
	private String identificador;
	
	@NotBlank(message = "Campo de preenchimento obrigatório!")
	private String chaveAcesso;
	
	public UsuarioAcessarRequestTransfer() {}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

}
