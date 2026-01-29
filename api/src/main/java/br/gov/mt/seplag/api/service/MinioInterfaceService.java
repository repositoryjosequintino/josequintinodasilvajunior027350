package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArquivoEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public interface MinioInterfaceService {
	List<ArquivoEntity> upload(AlbumEntity albumEntity, List<MultipartFile> multipartFileList);
	InputStream download(String endereco);

}
