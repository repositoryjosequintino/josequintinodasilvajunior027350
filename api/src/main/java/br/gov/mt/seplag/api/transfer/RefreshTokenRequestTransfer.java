package br.gov.mt.seplag.api.transfer;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequestTransfer {
	
	@NotBlank(message = "Campo obrigatório")
    private String refreshToken;
    
    public RefreshTokenRequestTransfer() {}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
