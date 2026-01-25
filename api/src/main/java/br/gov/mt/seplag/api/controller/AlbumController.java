package br.gov.mt.seplag.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.gov.mt.seplag.api.service.AlbumService;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumResponseTransfer;
import br.gov.mt.seplag.api.transfer.AlbumUploadCapaResponseTransfer;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    private final AlbumService albumService;

    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
	
	@PostMapping
	public ResponseEntity<AlbumResponseTransfer> create(@RequestBody @Valid AlbumRequestTransfer albumRequestTransfer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.albumService.create(albumRequestTransfer));
	}
	
	@PostMapping(
		value = "/upload/capa",
		consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<AlbumUploadCapaResponseTransfer> uploadCapa(
			@RequestPart(value = "albumRequestTransfer", required = true) @Valid AlbumRequestTransfer albumRequestTransfer,
			@RequestPart(value = "multipartFileList", required = true) List<MultipartFile> multipartFileList) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.albumService.uploadCapa(albumRequestTransfer, multipartFileList));
	}

}
