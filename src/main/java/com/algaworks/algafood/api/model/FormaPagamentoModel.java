package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Forma de Pagamento", description = "Representa uma Forma de Pagamento")
@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Cartão de Crédito")
	private String descricao;

}
