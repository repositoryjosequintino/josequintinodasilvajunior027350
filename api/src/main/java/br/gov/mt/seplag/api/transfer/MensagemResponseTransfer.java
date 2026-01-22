package br.gov.mt.seplag.api.transfer;

public class MensagemResponseTransfer {
	
	private String mensagem;
	
	public MensagemResponseTransfer() {}

	public MensagemResponseTransfer(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
