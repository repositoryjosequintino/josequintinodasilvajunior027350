package br.gov.mt.seplag.api.transfer;

public class UsuarioResponseTransfer {
	
	private String code;
	
    private String nome;
    
    private String perfil;

    private String identificador;
    
    private String token;
    
    private String isContaVerificada;
    
    private String createdAt;

    public UsuarioResponseTransfer() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsContaVerificada() {
		return isContaVerificada;
	}

	public void setIsContaVerificada(String isContaVerificada) {
		this.isContaVerificada = isContaVerificada;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
