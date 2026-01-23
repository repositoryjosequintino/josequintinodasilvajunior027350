package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.repository.AlbumRepository;

@Service
public class AlbumService {
	
	private final AlbumRepository albumRepository;

	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

}
