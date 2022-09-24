package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma Cidade")
@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {
	
//	@ApiModelProperty(value = "Id da cidade", example = "1") muito óbvio "Id da cidade", não precisa
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Rio Claro")
	private String nome;
	
	private EstadoModel estado;

}
