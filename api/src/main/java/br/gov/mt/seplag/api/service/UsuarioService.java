package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.seplag.api.mapper.UsuarioMapper;
import br.gov.mt.seplag.api.repository.UsuarioRepository;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	public UsuarioResponseTransfer create(UsuarioRequestTransfer usuarioRequestTransfer) {
		return UsuarioMapper.from(this.usuarioRepository.save(UsuarioMapper.from(usuarioRequestTransfer)));
	}

}
