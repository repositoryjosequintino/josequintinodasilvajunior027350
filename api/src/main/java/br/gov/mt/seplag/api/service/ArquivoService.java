package br.gov.mt.seplag.api.service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArquivoEntity;
import br.gov.mt.seplag.api.entity.ArtistaAlbumEntity;
import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import br.gov.mt.seplag.api.mapper.AlbumMapper;
import br.gov.mt.seplag.api.repository.AlbumRepository;
import br.gov.mt.seplag.api.repository.ArquivoRepository;
import br.gov.mt.seplag.api.repository.ArtistaAlbumRepository;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumUploadCapaResponseTransfer;
import br.gov.mt.seplag.api.transfer.ArquivoResponseTransfer;

@Service
public class ArquivoService {

    private final ArquivoRepository arquivoRepository;

    private final MinioService minioService;

    private final AlbumRepository albumRepository;

    private final ArtistaRepository artistaRepository;

    private final ArtistaAlbumRepository artistaAlbumRepository;

    private final ArtistaService artistaService;

    public ArquivoService(ArquivoRepository arquivoRepository, MinioService minioService,
                          AlbumRepository albumRepository, ArtistaRepository artistaRepository,
                          ArtistaAlbumRepository artistaAlbumRepository, ArtistaService artistaService) {
        this.arquivoRepository = arquivoRepository;
        this.minioService = minioService;
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
        this.artistaAlbumRepository = artistaAlbumRepository;
        this.artistaService = artistaService;
    }

    public AlbumUploadCapaResponseTransfer upload(AlbumRequestTransfer albumRequestTransfer, List<MultipartFile> multipartFileList) {
		
		ArtistaEntity artistaEntity = this.artistaService.recuperarArtista(albumRequestTransfer.getCodePublicArtista());
		
		if (!artistaEntity.getIsActive()) {
		    throw new NegocialException("O artista informado está inativo!");
		}
		
		if (artistaAlbumRepository.existsAlbumPorArtista(artistaEntity.getCode(), albumRequestTransfer.getTitulo())) {
			throw new NegocialException("O artista já possui um álbum cadastrado com esse título!");
		}
		
		AlbumEntity albumEntity = albumRepository.save(AlbumMapper.toEntity(albumRequestTransfer));
		
		artistaAlbumRepository.save(new ArtistaAlbumEntity(artistaEntity, albumEntity));
		
		List<ArquivoEntity> arquivoEntityList = minioService.upload(albumEntity, multipartFileList);
		
        List<ArquivoEntity> arquivoPersistenciaList = arquivoEntityList.stream()
                .map(arquivo -> new ArquivoEntity(
                        this.removerExtencao(arquivo.getNome()),
                        arquivo.getTamanho(),
                        this.recuperarExtensao(arquivo.getExtensao()),
                        arquivo.getEndereco()))
                .map(arquivoRepository::save)
                .toList();

		List<ArquivoResponseTransfer> arquivoResponseTransferList = arquivoPersistenciaList.stream()
	            .map(arquivo -> new ArquivoResponseTransfer(
	                    arquivo.getCodePublic(),
	                    this.removerExtencao(arquivo.getNome()),
	                    this.recuperarExtensao(arquivo.getExtensao()),
	                    "/api/v1/arquivo/" + arquivo.getCodePublic()
	            )).toList();

		return new AlbumUploadCapaResponseTransfer(
			albumEntity.getCodePublic(),
			albumEntity.getTitulo(),
			arquivoResponseTransferList,
			String.valueOf(albumEntity.getCreatedAt())
		);
	}

    private String removerExtencao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains(".")) {
            return nomeArquivo;
        }
        return nomeArquivo.substring(0, nomeArquivo.lastIndexOf('.'));
    }

    private String recuperarExtensao(String nomeArquivo) {

        if (nomeArquivo == null || !nomeArquivo.contains(".")) {
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        String extensao = nomeArquivo.substring(
            nomeArquivo.lastIndexOf('.') + 1).toLowerCase();

        return switch (extensao) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG_VALUE;
            case "png" -> MediaType.IMAGE_PNG_VALUE;
            case "gif" -> MediaType.IMAGE_GIF_VALUE;
            case "webp" -> "image/webp";
            default -> throw new NegocialException("(ArquivoService) Formato de arquivo não suportado!");
        };
    }

    public ArquivoResponseTransfer download(UUID codePublic) {
        
        ArquivoEntity arquivoEntity = this.arquivoRepository.findByCodePublic(codePublic)
                .orElseThrow(() -> new NegocialException("[ArquivoService] Erro ao tentar recuperar o arquivo!"));
        
        
        InputStream inputStream = this.minioService.download(arquivoEntity.getEndereco());
        
        return new ArquivoResponseTransfer(
            arquivoEntity.getCodePublic(),
            arquivoEntity.getNome(),
            arquivoEntity.getExtensao(),
            arquivoEntity.getEndereco(),
            inputStream
        );

    }

}
