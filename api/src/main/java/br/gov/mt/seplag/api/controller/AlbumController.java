package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.service.AlbumService;
import br.gov.mt.seplag.api.service.ArquivoService;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumResponseTransfer;
import br.gov.mt.seplag.api.transfer.AlbumUploadCapaResponseTransfer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    private final AlbumService albumService;

	private final ArquivoService arquivoService;

    AlbumController(AlbumService albumService, ArquivoService arquivoService) {
        this.albumService = albumService;
		this.arquivoService = arquivoService;
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
		return ResponseEntity.status(HttpStatus.CREATED).body(this.arquivoService.upload(albumRequestTransfer, multipartFileList));
	}

	// @GetMapping("/{codePublic}")
	// public ResponseEntity<byte[]> recuperarCapaAlbum(@PathVariable UUID codePublic) {
	// 	ArquivoResponseTransfer arquivoResponseTransfer = albumService.recuperarCapaAlbum(codePublic);
	// 	return ResponseEntity.ok()
	// 			.contentType(MediaType.parseMediaType(arquivoResponseTransfer.getExtensao()))
	// 			.header(
	// 					HttpHeaders.CONTENT_DISPOSITION,
	// 					"inline; filename=\"" + arquivoResponseTransfer.getCodePublic() + "\"")
	// 			.body(new InputStreamResource(arquivoResponseTransfer.getInputStream()));

	// }

}
