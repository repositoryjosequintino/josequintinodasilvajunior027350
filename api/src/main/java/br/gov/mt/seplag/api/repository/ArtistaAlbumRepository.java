package br.gov.mt.seplag.api.repository;

import br.gov.mt.seplag.api.entity.AlbumEntity;
import br.gov.mt.seplag.api.entity.ArtistaAlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistaAlbumRepository extends JpaRepository<ArtistaAlbumEntity, Long> {

	@Query(value = """
			SELECT EXISTS (
			    SELECT 1
			    FROM TB_ARTISTA_ALBUM ARTISTA_ALBUM
			    JOIN TB_ALBUM ALBUM ON ALBUM.CODE = ARTISTA_ALBUM.ID_ALBUM
			    WHERE ARTISTA_ALBUM.ID_ARTISTA = :artistaCode
			      AND UPPER(ALBUM.TITULO) = UPPER(:titulo)
			      AND ARTISTA_ALBUM.IS_ACTIVE = TRUE
			      AND ALBUM.IS_ACTIVE = TRUE
			)
	""", nativeQuery = true)
	boolean existsAlbumPorArtista(@Param("artistaCode") Long artistaCode, @Param("titulo") String titulo);

	Optional<AlbumEntity> findByCodePublic(UUID codePublic);

}
