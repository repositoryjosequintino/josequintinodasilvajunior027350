package br.gov.mt.seplag.api.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", unique = true, nullable = false)
    private Long code;

    @Column(name = "code_public", unique = true, nullable = false)
    private UUID codePublic = UUID.randomUUID();

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    
    @Column(name = "perfil", nullable = false)
    private String perfil;

    @Column(name = "identificador", unique = true, nullable = false)
    private String identificador;

    @Column(name = "chave_acesso", nullable = false)
    private String chaveAcesso;
    
    @Column(name = "CHAVE_ACESSO_ANTERIOR")
    private String chaveAcessoAnterior;

    @Column(name = "token", length = 400, unique = true)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 400, unique = true)
    private String refreshToken;

    @Column(name = "is_conta_verificada")
    private Boolean isContaVerificada = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    public UsuarioEntity() {}

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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public String getChaveAcessoAnterior() {
		return chaveAcessoAnterior;
	}

	public void setChaveAcessoAnterior(String chaveAcessoAnterior) {
		this.chaveAcessoAnterior = chaveAcessoAnterior;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Boolean getIsContaVerificada() {
		return isContaVerificada;
	}

	public void setIsContaVerificada(Boolean isContaVerificada) {
		this.isContaVerificada = isContaVerificada;
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
