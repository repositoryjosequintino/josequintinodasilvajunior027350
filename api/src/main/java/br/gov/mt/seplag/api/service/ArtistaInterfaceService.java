package br.gov.mt.seplag.api.service;

import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.transfer.ArtistaRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaResponseTransfer;

@Service
public interface ArtistaInterfaceService {
	
	ArtistaResponseTransfer cadastrarArtista(ArtistaRequestTransfer artistaRequestTransfer);

}
