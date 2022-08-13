package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data  // do Lombok - implementa getters, setters, equal & hash code ...
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // para especificar que não quero todos atributos, só o ID.
@Entity
public class FormaPagamento {
	
	@EqualsAndHashCode.Include // especificando que quero só o Id.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String descricao;

	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
}
