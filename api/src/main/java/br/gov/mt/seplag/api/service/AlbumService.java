package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArquivoEntity;
import br.gov.mt.seplag.api.entity.ArtistaAlbumEntity;
import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import br.gov.mt.seplag.api.mapper.AlbumMapper;
import br.gov.mt.seplag.api.repository.AlbumRepository;
import br.gov.mt.seplag.api.repository.ArtistaAlbumRepository;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumResponseTransfer;
import br.gov.mt.seplag.api.transfer.AlbumUploadCapaResponseTransfer;
import br.gov.mt.seplag.api.transfer.ArquivoResponseTransfer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AlbumService {
	
	private final AlbumRepository albumRepository;
	
	private final ArtistaRepository artistaRepository;
	
	private final ArtistaAlbumRepository artistaAlbumRepository;
	
	private final MinioService minioService;
	
	public AlbumService(
			AlbumRepository albumRepository, 
			ArtistaRepository artistaRepository,
			ArtistaAlbumRepository artistaAlbumRepository, 
			MinioService minioService) {
		this.albumRepository = albumRepository;
		this.artistaRepository = artistaRepository;
		this.artistaAlbumRepository = artistaAlbumRepository;
		this.minioService = minioService;
	}

	@Transactional
	public AlbumResponseTransfer create(AlbumRequestTransfer albumRequestTransfer) {
		try {
			
			ArtistaEntity artistaEntity = validarDadosAlbum(albumRequestTransfer);
			
			AlbumEntity albumEntity = AlbumMapper.toEntity(albumRequestTransfer);
			
			this.albumRepository.save(albumEntity);
			
			ArtistaAlbumEntity artistaAlbumEntity = new ArtistaAlbumEntity(artistaEntity, albumEntity);
			
			this.artistaAlbumRepository.save(artistaAlbumEntity);
			
			return AlbumMapper.toTransfer(albumEntity);
			
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new NegocialException("Falha ao tentar cadastrar um novo álbum!");
		}
	}

	private ArtistaEntity validarDadosAlbum(AlbumRequestTransfer albumRequestTransfer) {
		ArtistaEntity artistaEntity = this.artistaRepository
				.findByCodePublic(albumRequestTransfer.getCodePublicArtista())
				.orElseThrow(() -> new NegocialException("O artista informado não está cadastrado!"));
		
		if (!artistaEntity.getIsActive()) {
		    throw new NegocialException("O artista informado está inativo!");
		}
		
		if (artistaAlbumRepository.existsAlbumPorArtista(artistaEntity.getCode(), albumRequestTransfer.getTitulo())) {
			throw new NegocialException("O artista já possui um álbum cadastrado com esse título!");
		}
		return artistaEntity;
	}
	
	public AlbumUploadCapaResponseTransfer uploadCapa(
		AlbumRequestTransfer albumRequestTransfer,
		List<MultipartFile> multipartFileList) {
		
		ArtistaEntity artistaEntity = validarDadosAlbum(albumRequestTransfer);
		
		AlbumEntity albumEntity = albumRepository.save(AlbumMapper.toEntity(albumRequestTransfer));
		
		artistaAlbumRepository.save(new ArtistaAlbumEntity(artistaEntity, albumEntity));
		
		List<ArquivoEntity> arquivoEntityList = minioService.upload(albumEntity, multipartFileList);
		
		List<ArquivoResponseTransfer> arquivoResponseTransferList = arquivoEntityList.stream()
	            .map(arquivo -> new ArquivoResponseTransfer(
	                    arquivo.getCodePublic(),
	                    arquivo.getNome(),
	                    arquivo.getExtensao(),
	                    "/api/v1/arquivo/" + arquivo.getCodePublic()
	            )).toList();
		
		return new AlbumUploadCapaResponseTransfer(
			albumEntity.getCodePublic(),
			albumEntity.getTitulo(),
			arquivoResponseTransferList,
			String.valueOf(albumEntity.getCreatedAt())
		);
	}

	// public ArquivoResponseTransfer recuperarCapaAlbum(UUID codePublic) {

	// 	AlbumEntity albumEntity = this.artistaAlbumRepository.findByCodePublic(codePublic)
	// 			.orElseThrow(() -> new NegocialException("Álbum não encontrado!"));

	// 	List<ArquivoEntity> arquivoEntityList = minioService.uploadCapaAlbum(albumEntity, multipartFileList);

	// 	InputStream inputStream = minioService.download(arquivoEntityList);

	// 	return null;
	// }
	
}
