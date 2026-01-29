package br.gov.mt.seplag.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.seplag.api.entity.ArquivoEntity;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoEntity, Long> {
    Optional<ArquivoEntity> findByCodePublic(UUID codePublic);
}
