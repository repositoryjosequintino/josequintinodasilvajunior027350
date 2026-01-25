package br.gov.mt.seplag.api.transfer;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlbumRequestTransfer {
	
	@NotNull(message = "O campo deve ser preenchido!")
	private UUID codePublicArtista;
	
	@NotBlank(message = "O campo deve ser preenchido!")
	private String titulo;
	
	public AlbumRequestTransfer() {}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public UUID getCodePublicArtista() {
		return codePublicArtista;
	}

	public void setCodePublicArtista(UUID codePublicArtista) {
		this.codePublicArtista = codePublicArtista;
	}

}
