package br.gov.mt.seplag.api.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlbumUploadCapaResponseTransfer {
	
	private UUID codePublic;

    private String titulo;
    
    private List<ArquivoResponseTransfer> arquivoResponseTransferList = new ArrayList<>();
    
    private String createdAt;

    private String updatedAt;

    private String deletedAt;

    private String isActive;
	
	public AlbumUploadCapaResponseTransfer() {}

	public AlbumUploadCapaResponseTransfer(UUID codePublic, String titulo,
			List<ArquivoResponseTransfer> arquivoResponseTransferList, String createdAt) {
		this.codePublic = codePublic;
		this.titulo = titulo;
		this.arquivoResponseTransferList = arquivoResponseTransferList;
		this.createdAt = createdAt;
	}

	public UUID getCodePublic() {
		return codePublic;
	}

	public void setCodePublic(UUID codePublic) {
		this.codePublic = codePublic;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<ArquivoResponseTransfer> getArquivoResponseTransferList() {
		return arquivoResponseTransferList;
	}

	public void setArquivoResponseTransferList(List<ArquivoResponseTransfer> arquivoResponseTransferList) {
		this.arquivoResponseTransferList = arquivoResponseTransferList;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
