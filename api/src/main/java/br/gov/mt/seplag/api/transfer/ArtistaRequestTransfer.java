package br.gov.mt.seplag.api.transfer;

import jakarta.validation.constraints.NotBlank;

public class ArtistaRequestTransfer {
	
	@NotBlank(message = "O campo deve ser preenchido!")
	private String nome;
	
	public ArtistaRequestTransfer() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
