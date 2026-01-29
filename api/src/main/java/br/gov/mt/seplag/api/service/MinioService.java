package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArquivoEntity;
import br.gov.mt.seplag.api.exception.NegocialException;
import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MinioService implements MinioInterfaceService {

	private static final String BUCKET = "seplagmt-storage";

	private final MinioClient minioClient;

	private static final Logger log = LoggerFactory.getLogger(MinioService.class);

	public MinioService(MinioClient minioClient) {
		this.minioClient = minioClient;
	}

	@Override
	public List<ArquivoEntity> upload(AlbumEntity albumEntity, List<MultipartFile> multipartFileList) {
		
		if (multipartFileList == null || multipartFileList.isEmpty()) {
			throw new NegocialException("É obrigatório enviar ao menos uma capa para o álbum.");
		}

		criarBucketSeNaoExistir();

		List<ArquivoEntity> arquivosSalvos = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFileList) {

			validarArquivo(multipartFile);

			String extensao = obterExtensao(multipartFile.getOriginalFilename());
			String nomeArquivo = UUID.randomUUID() + "." + extensao;

			String caminho = String.format("albums/%s/%s", albumEntity.getCodePublic(), nomeArquivo);

			try {
				minioClient
					.putObject(PutObjectArgs.builder()
					.bucket(BUCKET)
					.object(caminho)
					.stream(multipartFile.getInputStream(), multipartFile.getSize(), -1).contentType(multipartFile.getContentType()).build());
			} catch (Exception e) {
				throw new RuntimeException("Erro ao enviar arquivo para o MinIO", e);
			}

			ArquivoEntity arquivoEntity = new ArquivoEntity(multipartFile.getOriginalFilename(), String.valueOf(multipartFile.getSize()),
					extensao, caminho);

			arquivosSalvos.add(arquivoEntity);
		}

		return arquivosSalvos;
	}

	private void criarBucketSeNaoExistir() {
		try {
			boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET).build());

			if (!exists) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET).build());
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao verificar/criar bucket no MinIO", e);
		}
	}

	private void validarArquivo(MultipartFile file) {
		if (file.isEmpty()) {
			throw new NegocialException("Arquivo inválido ou vazio.");
		}

		if (!file.getContentType().startsWith("image/")) {
			throw new NegocialException("Apenas arquivos de imagem são permitidos.");
		}
	}

	private String obterExtensao(String nomeArquivo) {
		return nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1);
	}

	public String gerarLinkTemporario(String caminho) {
		try {
			return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET)
					.bucket(BUCKET).object(caminho).expiry(30 * 60).build());
		} catch (Exception exception) {
			throw new RuntimeException("Erro ao gerar URL temporária", exception);
		}
	}

	@Override
	public InputStream download(String endereco) {
		try {
			return minioClient.getObject(GetObjectArgs.builder().bucket(BUCKET).object(endereco).build());
		} catch (Exception exception) {
			log.info("[MinioService] Erro ao tentar realizar download do arquivo!");
			throw new RuntimeException("[MinIoServer] Erro ao tentar realizar download do arquivo!", exception);
		}
	}

}
