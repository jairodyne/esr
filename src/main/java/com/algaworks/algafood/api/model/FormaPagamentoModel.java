package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Forma de Pagamento", description = "Representa uma Forma de Pagamento")
@Getter
@Setter
public class FormaPagamentoModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Cartão de Crédito")
	private String descricao;

}
