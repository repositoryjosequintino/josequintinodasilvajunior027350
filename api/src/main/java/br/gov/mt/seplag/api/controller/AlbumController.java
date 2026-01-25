package br.gov.mt.seplag.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.api.service.AlbumService;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumResponseTransfer;
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

}
