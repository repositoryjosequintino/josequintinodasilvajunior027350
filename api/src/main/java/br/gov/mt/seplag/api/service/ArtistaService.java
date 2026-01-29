package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import br.gov.mt.seplag.api.mapper.ArtistaMapper;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import br.gov.mt.seplag.api.transfer.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
	
	public PaginatedResponseTransfer<ArtistaResponseTransfer> findAll(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<ArtistaEntity> artistaPage = this.artistaRepository.findAll(pageable);

		List<ArtistaResponseTransfer> artistaResponseTransferList = artistaPage.getContent().stream()
				.map(artista -> ArtistaMapper.from(artista)).collect(Collectors.toList());

		return new PaginatedResponseTransfer<>(artistaResponseTransferList, artistaPage.getNumber(),
				artistaPage.getSize(), artistaPage.getTotalElements(), artistaPage.getTotalPages(),
				artistaPage.isFirst(), artistaPage.isLast());

	}
	
	public ArtistaResponseTransfer findOne(UUID codePublic) {
		
		ArtistaEntity artistaEntity = this.artistaRepository.findByCodePublic(codePublic)
				.orElseThrow(() -> new NegocialException("Artista não encontrado!"));
		
		return ArtistaMapper.from(artistaEntity);
		
	}

	public ArtistaEntity recuperarArtista(UUID codePublicArtista) {
		return this.artistaRepository
				.findByCodePublic(codePublicArtista)
				.orElseThrow(() -> new NegocialException("O artista informado não está cadastrado!"));
	}

	public List<ArtistaResponseTransfer> recuperarArtista(String nome, String sort, int page, int size) {

		Sort.Direction direction = "desc".equalsIgnoreCase(sort) ? Sort.Direction.DESC : Sort.Direction.ASC;

		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "nome"));

		Page<ArtistaEntity> artistaPage = this.artistaRepository.findByNome(nome, pageable);

		List<ArtistaResponseTransfer> artistaResponseTransferList =
				artistaPage.getContent()
						.stream()
						.map(ArtistaMapper::from)
						.toList();

		return new PaginatedResponseTransfer<>(
				artistaResponseTransferList,
				artistaPage.getNumber(),
				artistaPage.getSize(),
				artistaPage.getTotalElements(),
				artistaPage.getTotalPages(),
				artistaPage.isFirst(),
				artistaPage.isLast()
		).getContent();
	}

}
