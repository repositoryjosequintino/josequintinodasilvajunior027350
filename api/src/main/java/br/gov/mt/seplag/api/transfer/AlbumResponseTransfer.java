package br.gov.mt.seplag.api.transfer;

public class AlbumResponseTransfer {
	
	private String codePublic;

    private String titulo;
    
    private String createdAt;

    private String updatedAt;

    private String deletedAt;

    private String isActive;
	
	public AlbumResponseTransfer() {}

	public AlbumResponseTransfer(String codePublic, String titulo, String createdAt, String updatedAt, String deletedAt,
			String isActive) {
		this.codePublic = codePublic;
		this.titulo = titulo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActive = isActive;
	}

	public String getCodePublic() {
		return codePublic;
	}

	public void setCodePublic(String codePublic) {
		this.codePublic = codePublic;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
