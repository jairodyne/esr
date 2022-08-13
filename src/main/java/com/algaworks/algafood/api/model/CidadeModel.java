package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma Cidade")
@Getter
@Setter
public class CidadeModel {
	
//	@ApiModelProperty(value = "Id da cidade", example = "1") muito óbvio "Id da cidade", não precisa
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Rio Claro")
	private String nome;
	
	private EstadoModel estado;

}
