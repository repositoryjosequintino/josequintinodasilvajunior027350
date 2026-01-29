package br.gov.mt.seplag.api.transfer;

import java.io.InputStream;
import java.util.UUID;

public class ArquivoResponseTransfer {
	
	private UUID codePublic;
	
    private String nome;
    
    private String extensao;
    
    private String endereco;

	private InputStream inputStream;
    
	public ArquivoResponseTransfer(UUID codePublic, String nome, String extensao, String endereco) {
		this.codePublic = codePublic;
		this.nome = nome;
		this.extensao = extensao;
		this.endereco = endereco;
	}

	public ArquivoResponseTransfer(UUID codePublic, String nome, String extensao, String endereco, InputStream inputStream) {
		this.codePublic = codePublic;
		this.nome = nome;
		this.extensao = extensao;
		this.endereco = endereco;
		this.inputStream = inputStream;
	}

	public UUID getCodePublic() {
		return codePublic;
	}

	public void setCodePublic(UUID codePublic) {
		this.codePublic = codePublic;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
