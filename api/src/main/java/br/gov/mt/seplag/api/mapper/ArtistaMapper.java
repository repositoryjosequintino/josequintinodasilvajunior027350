package br.gov.mt.seplag.api.mapper;

import java.time.Instant;

import br.gov.mt.seplag.api.entity.ArtistaEntity;
import br.gov.mt.seplag.api.transfer.ArtistaAtualizacaoRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaRequestTransfer;
import br.gov.mt.seplag.api.transfer.ArtistaResponseTransfer;

public class ArtistaMapper {
	
	public static ArtistaEntity from(ArtistaRequestTransfer artistaRequestTransfer) {
		return new ArtistaEntity(artistaRequestTransfer.getNome());
	}
	
	public static ArtistaResponseTransfer from(ArtistaEntity artistaEntity) {
		return new ArtistaResponseTransfer(
				String.valueOf(artistaEntity.getCodePublic()), 
				artistaEntity.getNome(),
				String.valueOf(artistaEntity.getCreatedAt()), 
				String.valueOf(artistaEntity.getUpdatedAt()),
				String.valueOf(artistaEntity.getDeletedAt()), 
				String.valueOf(artistaEntity.getIsActive()));
	}
	
	public static ArtistaEntity update(ArtistaEntity artistaEntity,
			ArtistaAtualizacaoRequestTransfer artistaAtualizacaoRequestTransfer) {
		artistaEntity.setNome(artistaAtualizacaoRequestTransfer.getNome());
		artistaEntity.setUpdatedAt(Instant.now());
		return artistaEntity;
	}

}
