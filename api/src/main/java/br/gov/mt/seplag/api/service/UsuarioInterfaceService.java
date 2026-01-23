package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

@Service
public interface UsuarioInterfaceService {

	UsuarioResponseTransfer obterInformaoUsuario(String identificador);
	
}
