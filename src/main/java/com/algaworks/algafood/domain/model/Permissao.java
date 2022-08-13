package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data  // do Lombok - implementa getters, setters, equal & hash code ...
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // para especificar que não quero todos atributos, só o ID.
@Entity
public class Permissao {
	
	@EqualsAndHashCode.Include // especificando que quero só o Id.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
}
