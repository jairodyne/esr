package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter") retirado por conta da aula 13.6. Implementando pesquisas complexas na API
@Getter
@Setter
public class PedidoResumoModel {
	
	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;

}
