package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.service.ArtistaService;
import br.gov.mt.seplag.api.transfer.ArtistaAtualizacaoRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaResponseTransfer;
import br.gov.mt.seplag.api.transfer.MensagemResponseTransfer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.artistaService.cadastrarArtista(artistaRequestTransfer));
	}
	
	@PutMapping("/{codePublic}")
	public ResponseEntity<ArtistaResponseTransfer> update(
			@PathVariable UUID codePublic,
			@RequestBody @Valid ArtistaAtualizacaoRequestTransfer artistaAtualizacaoRequestTransfer,
			Authentication authentication
			) {
		return ResponseEntity.ok(this.artistaService.update(codePublic, artistaAtualizacaoRequestTransfer));
	}
	
	@DeleteMapping("/{codePublic}")
	public ResponseEntity<MensagemResponseTransfer> delete(
			@PathVariable UUID codePublic,
			Authentication authentication) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.artistaService.delete(codePublic));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			Authentication authentication) {
		return ResponseEntity.ok(this.artistaService.findAll(page, size));
	}
	
	@GetMapping("/{codePublic}")
	public ResponseEntity<ArtistaResponseTransfer> findOne(@PathVariable UUID codePublic) {
		return ResponseEntity.ok(this.artistaService.findOne(codePublic));
	}

	@GetMapping("/pesquisar")
	public ResponseEntity<?> recuperarArtista(
			@RequestParam String nome,
			@RequestParam(defaultValue = "asc") String sort,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			Authentication authentication) {
		return ResponseEntity.ok(artistaService.recuperarArtista(nome, sort, page, size));
	}

}
