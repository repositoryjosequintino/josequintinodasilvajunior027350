package br.gov.mt.seplag.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArquivoEntity;

@Service
public interface MinioInterfaceService {
	List<ArquivoEntity> uploadCapaAlbum(AlbumEntity albumEntity, List<MultipartFile> multipartFileList);
}
