package br.gov.mt.seplag.api.mapper;

import br.gov.mt.seplag.api.entity.UsuarioEntity;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;

public class UsuarioMapper {
	
	public static UsuarioEntity from(UsuarioRequestTransfer usuarioRequestTransfer) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
			usuarioEntity.setNome(usuarioRequestTransfer.getNome());
			usuarioEntity.setPerfil(usuarioRequestTransfer.getPerfil());
			usuarioEntity.setIdentificador(usuarioRequestTransfer.getIdentificador());
			usuarioEntity.setChaveAcesso(usuarioRequestTransfer.getChaveAcesso());
		return usuarioEntity;
	}
	
	public static UsuarioResponseTransfer from(UsuarioEntity usuarioEntity) {
		UsuarioResponseTransfer usuarioResponseTransfer = new UsuarioResponseTransfer();
			usuarioResponseTransfer.setCode(usuarioEntity.getCodePublic().toString());
			usuarioResponseTransfer.setCreatedAt(String.valueOf(usuarioEntity.getCreatedAt()));
			usuarioResponseTransfer.setIdentificador(usuarioEntity.getIdentificador());
			usuarioResponseTransfer.setIsContaVerificada(String.valueOf(usuarioEntity.getIsContaVerificada()));
			usuarioResponseTransfer.setNome(usuarioEntity.getNome());
			usuarioResponseTransfer.setPerfil(usuarioEntity.getPerfil());
			usuarioResponseTransfer.setAccessToken(usuarioEntity.getAccessToken());
			usuarioResponseTransfer.setRefreshToken(usuarioEntity.getRefreshToken());
		return usuarioResponseTransfer;
	}

}
