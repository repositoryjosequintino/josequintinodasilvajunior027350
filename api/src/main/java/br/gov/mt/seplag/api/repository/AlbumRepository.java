package br.gov.mt.seplag.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.seplag.api.entity.ArtistaEntity;

@Repository
public interface AlbumRepository extends JpaRepository<ArtistaEntity, Long> {}
