package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.entity.UsuarioEntity;
import br.gov.mt.seplag.api.mapper.UsuarioMapper;
import br.gov.mt.seplag.api.repository.UsuarioRepository;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

@Service
public class UsuarioService implements UsuarioInterfaceService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UsuarioResponseTransfer obterInformaoUsuario(String identificador) {
		UsuarioEntity usuarioEntity = this.usuarioRepository.findByIdentificador(identificador)
				.orElseThrow(() -> new RuntimeException("Usuário Não Encontrado!"));
		return UsuarioMapper.from(usuarioEntity, null, null);
	}

}
