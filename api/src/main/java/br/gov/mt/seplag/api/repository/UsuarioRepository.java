package br.gov.mt.seplag.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.seplag.api.entity.UsuarioEntity;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	Optional<UsuarioEntity> findByIdentificador(String identificador);
	
}
