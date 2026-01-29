package br.gov.mt.seplag.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "TB_ARQUIVO")
public class ArquivoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE", unique = true, nullable = false)
    private Long code;

    @Column(name = "CODE_PUBLIC", unique = true, nullable = false)
    private UUID codePublic = UUID.randomUUID();

    @Column(name = "NOME", nullable = false)
    private String nome;
    
    @Column(name = "TAMANHO", nullable = false)
    private String tamanho;
    
    @Column(name = "EXTENSAO", nullable = false)
    private String extensao;
    
    @Column(name = "ENDERECO", nullable = false)
    private String endereco;
    
    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

    @Column(name = "DELETED_AT")
    private Instant deletedAt;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = true;
    
    public ArquivoEntity() {}

	public ArquivoEntity(String nome, String tamanho, String extensao, String endereco) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.extensao = extensao;
		this.endereco = endereco;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Instant getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Instant deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
