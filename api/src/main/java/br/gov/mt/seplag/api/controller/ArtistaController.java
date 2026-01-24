package br.gov.mt.seplag.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.api.service.ArtistaService;
import br.gov.mt.seplag.api.transfer.ArtistaRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaResponseTransfer;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/artista")
public class ArtistaController {
	
	private final ArtistaService artistaService;

	public ArtistaController(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}
	
	@PostMapping
	public ResponseEntity<ArtistaResponseTransfer> cadastrarArtista(
			@RequestBody @Valid ArtistaRequestTransfer artistaRequestTransfer) {
		return ResponseEntity.ok().body(this.artistaService.cadastrarArtista(artistaRequestTransfer));
	}

}
