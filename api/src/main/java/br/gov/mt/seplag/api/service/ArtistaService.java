package br.gov.mt.seplag.api.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import br.gov.mt.seplag.api.mapper.ArtistaMapper;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import br.gov.mt.seplag.api.transfer.ArtistaAtualizacaoRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaResponseTransfer;
import br.gov.mt.seplag.api.transfer.MensagemResponseTransfer;

@Service
public class ArtistaService implements ArtistaInterfaceService {

	private final ArtistaRepository artistaRepository;

	public ArtistaService(ArtistaRepository artistaRepository) {
		this.artistaRepository = artistaRepository;
	}

	@Transactional
	@Override
	public ArtistaResponseTransfer cadastrarArtista(ArtistaRequestTransfer artistaRequestTransfer) {

		verificarRegistroDuplicado(artistaRequestTransfer);

		ArtistaEntity artistaEntity = ArtistaMapper.from(artistaRequestTransfer);

		try {
			artistaRepository.save(artistaEntity);
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new NegocialException("Já existe um artista cadastrado com esse nome!");
		}

		return ArtistaMapper.from(artistaEntity);

	}
	
	private void verificarRegistroDuplicado(ArtistaRequestTransfer artistaRequestTransfer) {
		if (this.artistaRepository.recuperarArtista(artistaRequestTransfer.getNome())) {
			throw new NegocialException("Já existe um artista cadastrado com esse nome!");
		}
	}

	@Transactional
	public ArtistaResponseTransfer update(UUID codePublic, ArtistaAtualizacaoRequestTransfer artistaAtualizacaoRequestTransfer) {
		
		ArtistaEntity artistaEntity = this.artistaRepository.findByCodePublic(codePublic)
				.orElseThrow(() -> new NegocialException("Artista não encontrado!"));

		if (!artistaEntity.getIsActive()) {
			throw new NegocialException("O registro informado está inativo!");
		}
		
		this.artistaRepository.save(ArtistaMapper.update(artistaEntity, artistaAtualizacaoRequestTransfer));

		return ArtistaMapper.from(artistaEntity);

	}
	
	public MensagemResponseTransfer delete(UUID codePublic) {
		
		ArtistaEntity artistaEntity = this.artistaRepository.findByCodePublic(codePublic)
				.orElseThrow(() -> new NegocialException("Artista não encontrado!"));
		
		if (!artistaEntity.getIsActive()) {
			throw new NegocialException("Esse artista já foi inativado!");
		}
		
		artistaEntity.setIsActive(false);
		artistaEntity.setDeletedAt(Instant.now());
		
		this.artistaRepository.save(artistaEntity);
		
		return new MensagemResponseTransfer("Artista inativado com sucesso!");
		
	}

}
