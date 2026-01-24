package br.gov.mt.seplag.api.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import br.gov.mt.seplag.api.mapper.ArtistaMapper;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import br.gov.mt.seplag.api.transfer.ArtistaRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaResponseTransfer;

@Service
public class ArtistaService implements ArtistaInterfaceService {

	private final ArtistaRepository artistaRepository;

	public ArtistaService(ArtistaRepository artistaRepository) {
		this.artistaRepository = artistaRepository;
	}

	@Transactional
	@Override
	public ArtistaResponseTransfer cadastrarArtista(ArtistaRequestTransfer artistaRequestTransfer) {
		
		if (this.artistaRepository.recuperarArtista(artistaRequestTransfer.getNome())) {
			throw new NegocialException("Já existe um artista cadastrado com esse nome!");
		}

		ArtistaEntity artistaEntity = ArtistaMapper.from(artistaRequestTransfer);
		
		try {
			artistaRepository.save(artistaEntity);
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
		    throw new NegocialException("Já existe um artista cadastrado com esse nome!");
		}
		
		return ArtistaMapper.from(artistaEntity);
		
	}

}
