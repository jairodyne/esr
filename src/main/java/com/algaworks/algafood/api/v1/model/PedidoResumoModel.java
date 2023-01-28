package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter") retirado por conta da aula 13.6. Implementando pesquisas complexas na API
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {
	
	private String codigo;

	private BigDecimal subtotal;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;

	private String status;

	private OffsetDateTime dataCriacao;	
	
	private RestauranteApenasNomeModel restaurante;
	
	private UsuarioModel cliente;

}
