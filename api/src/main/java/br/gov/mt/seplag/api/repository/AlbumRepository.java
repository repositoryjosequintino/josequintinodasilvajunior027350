package br.gov.mt.seplag.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mt.seplag.api.entity.AlbumEntity;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
	
	@Query(value = """
			SELECT EXISTS (
				SELECT 1 
				FROM TB_ALBUM 
				WHERE UPPER(TITULO) = UPPER(:tituloParameter)
				AND IS_ACTIVE = TRUE
			)
			""", nativeQuery = true)
	boolean isAlbumExistente(@Param("tituloParameter") String titulo);
	
}
