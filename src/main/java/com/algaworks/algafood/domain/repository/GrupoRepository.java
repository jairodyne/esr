package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
	
	List<Grupo> findTodasByNomeContaining(String nome);
	Optional<Grupo> findByNome(String nome);
	
}
