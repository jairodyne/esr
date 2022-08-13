package com.algaworks.algafood.domain.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
	
	List<FormaPagamento> findTodasByDescricaoContaining(String descricao);
	Optional<FormaPagamento> findByDescricao(String descricao);
//	boolean existeByDescricao(String descricao);
	
	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getDataUltimaAtualizacao();
	
	@Query("select dataAtualizacao from FormaPagamento where id = :formaPagamentoId")
	OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);
	
}
