package br.gov.mt.seplag.api.repository;

import br.gov.mt.seplag.api.entity.ArtistaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;



@Repository
public interface ArtistaRepository extends JpaRepository<ArtistaEntity, Long> {
	
	@Query("""
		SELECT artista
		FROM ArtistaEntity artista
		WHERE artista.isActive = true
		AND UPPER(artista.nome) LIKE UPPER(CONCAT('%', :nomeParameter, '%'))
	""")
	Page<ArtistaEntity> findByNome(@Param("nomeParameter") String nome, Pageable pageable);
	
	@Query(value = """
			SELECT EXISTS (
			     SELECT 1
			     FROM TB_ARTISTA
			     WHERE UPPER(NOME) = UPPER(:nomeParameter)
			     AND IS_ACTIVE = TRUE
			 )
			""", nativeQuery = true)
	boolean recuperarArtista(@Param("nomeParameter") String nome);
	
	Optional<ArtistaEntity> findByCodePublic(UUID codePublic);

}
