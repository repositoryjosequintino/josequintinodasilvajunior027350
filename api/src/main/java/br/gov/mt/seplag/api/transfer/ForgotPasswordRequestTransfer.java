package br.gov.mt.seplag.api.transfer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequestTransfer {
	
	@Email(message = "O e-mail deve ser válido!")
	@NotBlank(message = "Campo obrigatório")
    private String identificador;
    
    public ForgotPasswordRequestTransfer() {}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

}
