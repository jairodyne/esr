package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data  // do Lombok - implementa getters, setters, equal & hash code ...
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // para especificar que não quero todos atributos, só o ID.
@Entity
public class FotoProduto {
	
	@EqualsAndHashCode.Include // especificando que quero só o Id.
	@Id
	@Column(name = "produto_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
	public Long getRestauranteId() {
		if(getProduto() != null) {
			return getProduto().getRestaurante().getId();
		}
		return null;
	}
	
}
