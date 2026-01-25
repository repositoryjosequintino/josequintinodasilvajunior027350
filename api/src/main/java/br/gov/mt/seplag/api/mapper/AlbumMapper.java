package br.gov.mt.seplag.api.mapper;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.transfer.AlbumRequestTransfer;
import br.gov.mt.seplag.api.transfer.AlbumResponseTransfer;

public class AlbumMapper {

	public static AlbumEntity toEntity(AlbumRequestTransfer albumRequestTransfer) {
		return new AlbumEntity(albumRequestTransfer.getTitulo());
	}

	public static AlbumResponseTransfer toTransfer(AlbumEntity albumEntity) {
		return new AlbumResponseTransfer(
				String.valueOf(albumEntity.getCodePublic()), 
				albumEntity.getTitulo(),
				String.valueOf(albumEntity.getCreatedAt()), 
				String.valueOf(albumEntity.getUpdatedAt()),
				String.valueOf(albumEntity.getDeletedAt()), 
				String.valueOf(albumEntity.getIsActive()));
	}

}
