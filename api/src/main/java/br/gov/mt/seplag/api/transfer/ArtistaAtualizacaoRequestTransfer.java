package br.gov.mt.seplag.api.transfer;

import jakarta.validation.constraints.NotBlank;

public class ArtistaAtualizacaoRequestTransfer {
	
	private Long code;
	
	@NotBlank(message = "O campo deve ser preenchido!")
	private String nome;
	
	public ArtistaAtualizacaoRequestTransfer() {}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
