package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.transfer.PaginatedResponseTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

@Service
public interface UsuarioInterfaceService {

	UsuarioResponseTransfer recuperarUsuario(String identificador);
	
	UsuarioResponseTransfer atualizarUsuario(UsuarioRequestTransfer usuarioRequestTransfer, String identificador);
	
	PaginatedResponseTransfer<UsuarioResponseTransfer> findAll(int page, int size);
	
}
