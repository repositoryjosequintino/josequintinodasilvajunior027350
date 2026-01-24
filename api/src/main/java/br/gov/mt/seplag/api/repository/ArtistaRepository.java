package br.gov.mt.seplag.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mt.seplag.api.entity.ArtistaEntity;



@Repository
public interface ArtistaRepository extends JpaRepository<ArtistaEntity, Long> {
	
	Optional<ArtistaEntity> findByNome(String nome);
	
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
