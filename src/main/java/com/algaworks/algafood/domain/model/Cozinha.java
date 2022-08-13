package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data  // do Lombok - implementa getters, setters, equal & hash code ...
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // para especificar que não quero todos atributos, só o ID.
@Entity
public class Cozinha {
	
	@NotNull(groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include // especificando que quero só o Id.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;

	@JsonIgnore  // para não causar referência circular na representação de Restaurante ( um fica chamando o outro em loop infinito )
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();

	
}
