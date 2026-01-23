package br.gov.mt.seplag.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.entity.UsuarioEntity;
import br.gov.mt.seplag.api.mapper.UsuarioMapper;
import br.gov.mt.seplag.api.repository.UsuarioRepository;
import br.gov.mt.seplag.api.transfer.PaginatedResponseTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

@Service
public class UsuarioService implements UsuarioInterfaceService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UsuarioResponseTransfer recuperarUsuario(String identificador) {
		UsuarioEntity usuarioEntity = this.usuarioRepository.findByIdentificador(identificador)
				.orElseThrow(() -> new RuntimeException("Usuário Não Encontrado!"));
		return UsuarioMapper.from(usuarioEntity, null, null);
	}

	@Override
	public UsuarioResponseTransfer atualizarUsuario(UsuarioRequestTransfer usuarioRequestTransfer, String identificador) {
		
		UsuarioEntity usuarioEntity = this.usuarioRepository.findByIdentificador(usuarioRequestTransfer.getIdentificador())
				.orElseThrow(() -> new RuntimeException("Usuário Não Encontrado!"));
		
		if (usuarioRequestTransfer != null && usuarioRequestTransfer.getNome().trim().isEmpty()) {
			usuarioEntity.setNome(usuarioRequestTransfer.getNome().trim());
		}
		
		this.usuarioRepository.save(usuarioEntity);
		
		return UsuarioMapper.from(usuarioEntity, null, null);
		
	}

	@Override
	public PaginatedResponseTransfer<UsuarioResponseTransfer> findAll(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<UsuarioEntity> usuarioPage = this.usuarioRepository.findAll(pageable);

		List<UsuarioResponseTransfer> usuarioResponseTransferList = usuarioPage.getContent().stream()
				.map(usuario -> UsuarioMapper.from(usuario, null, null)).collect(Collectors.toList());

		return new PaginatedResponseTransfer<>(usuarioResponseTransferList, usuarioPage.getNumber(),
				usuarioPage.getSize(), usuarioPage.getTotalElements(), usuarioPage.getTotalPages(),
				usuarioPage.isFirst(), usuarioPage.isLast());

	}

}
