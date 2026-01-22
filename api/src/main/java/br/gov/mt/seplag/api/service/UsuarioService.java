package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

}
