package br.gov.mt.seplag.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.api.service.UsuarioService;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/obter-informacao_usuario")
	public ResponseEntity<UsuarioResponseTransfer> obterInformaoUsuario(Authentication authentication) {
		return ResponseEntity.ok(this.usuarioService.obterInformaoUsuario(authentication.getName()));
	}

}
