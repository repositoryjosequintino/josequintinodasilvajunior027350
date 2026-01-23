package br.gov.mt.seplag.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.api.service.AutenticadorService;
import br.gov.mt.seplag.api.transfer.ForgotPasswordRequestTransfer;
import br.gov.mt.seplag.api.transfer.MensagemResponseTransfer;
import br.gov.mt.seplag.api.transfer.RefreshTokenRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioAcessarRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/autenticador")
public class AutenticadorController {
	
	private final AutenticadorService autenticadorService;

	public AutenticadorController(AutenticadorService autenticadorService) {
		this.autenticadorService = autenticadorService;
	}
	
	@PostMapping("/usuario/registrar")
	public ResponseEntity<MensagemResponseTransfer> registrarUsuario(@Valid @RequestBody UsuarioRequestTransfer usuarioRequestTransfer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.autenticadorService.registrarUsuario(usuarioRequestTransfer));
	}
	
	@PostMapping("/acessar")
	public ResponseEntity<UsuarioResponseTransfer> acessarConta(@Valid @RequestBody UsuarioAcessarRequestTransfer usuarioAcessarRequestTransfer) {
		return ResponseEntity.status(HttpStatus.OK).body(this.autenticadorService.acessarConta(usuarioAcessarRequestTransfer));
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<UsuarioResponseTransfer> refreshAccessToken(@Valid @RequestBody RefreshTokenRequestTransfer refreshTokenRequestTransfer) {
		return ResponseEntity.status(HttpStatus.OK).body(this.autenticadorService.refreshAccessToken(refreshTokenRequestTransfer));
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<MensagemResponseTransfer> forgotPassword(@RequestBody @Valid ForgotPasswordRequestTransfer forgotPasswordRequestTransfer) {
		return ResponseEntity.status(HttpStatus.OK).body(this.autenticadorService.forgotPassword(forgotPasswordRequestTransfer));
	}

}
