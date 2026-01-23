package br.gov.mt.seplag.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.api.service.ArtistaService;

@RestController
@RequestMapping("/api/v1/artista")
public class ArtistaController {
	
	private final ArtistaService artistaService;

	public ArtistaController(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}

}
