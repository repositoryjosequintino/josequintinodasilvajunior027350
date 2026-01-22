package br.gov.mt.seplag.api.transfer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRequestTransfer {
	
	@Size(min = 2, max = 100, message = "O nome deve conter entre 2 e 100 caracteres!")
	@NotBlank(message = "Campo obrigatório")
    private String nome;
    
	@Pattern(
			regexp = "^(ADMINISTRADOR|USUARIO)$", 
			message = "Os perfils aceitos são: ADMINISTRADOR ou USUARIO")
	@NotBlank(message = "Campo obrigatório")
    private String perfil;

	@Email(message = "O e-mail deve ser válido!")
	@NotBlank(message = "Campo obrigatório")
    private String identificador;

	@Size(min = 8, message = "A senha deve conter no minimo 8 caracteres!")
	@NotBlank(message = "Campo obrigatório")
    private String chaveAcesso;
    
    public UsuarioRequestTransfer() {}

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

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

}
