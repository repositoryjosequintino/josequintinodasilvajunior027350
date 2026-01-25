package br.gov.mt.seplag.api.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArtistaAlbumEntity;
import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import br.gov.mt.seplag.api.mapper.AlbumMapper;
import br.gov.mt.seplag.api.repository.AlbumRepository;
import br.gov.mt.seplag.api.repository.ArtistaAlbumRepository;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumResponseTransfer;

@Service
public class AlbumService {
	
	private final AlbumRepository albumRepository;
	
	private final ArtistaRepository artistaRepository;
	
	private final ArtistaAlbumRepository artistaAlbumRepository;

	public AlbumService(
			AlbumRepository albumRepository, 
			ArtistaRepository artistaRepository,
			ArtistaAlbumRepository artistaAlbumRepository) {
		this.albumRepository = albumRepository;
		this.artistaAlbumRepository = artistaAlbumRepository;
		this.artistaRepository = artistaRepository;
	}
	
	@Transactional
	public AlbumResponseTransfer create(AlbumRequestTransfer albumRequestTransfer) {
		try {
			
			ArtistaEntity artistaEntity = this.artistaRepository
					.findByCodePublic(albumRequestTransfer.getCodePublicArtista())
					.orElseThrow(() -> new NegocialException("O artista informado não está cadastrado!"));
			
			if (!artistaEntity.getIsActive()) {
		        throw new NegocialException("O artista informado está inativo!");
		    }
			
			if (artistaAlbumRepository.existsAlbumPorArtista(artistaEntity.getCode(), albumRequestTransfer.getTitulo())) {
				throw new NegocialException("O artista já possui um álbum cadastrado com esse título!");
			}
			
			AlbumEntity albumEntity = AlbumMapper.toEntity(albumRequestTransfer);
			
			this.albumRepository.save(albumEntity);
			
			ArtistaAlbumEntity artistaAlbumEntity = new ArtistaAlbumEntity(artistaEntity, albumEntity);
			
			this.artistaAlbumRepository.save(artistaAlbumEntity);
			
			return AlbumMapper.toTransfer(albumEntity);
			
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new NegocialException("Falha ao tentar cadastrar um novo álbum!");
		}
	}
	
}
