package com.algaworks.algafood;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO) ;
	
	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	StatusPedido(String descricao, StatusPedido... statusAnteriores ) { 
		// StatusPedido...  estes "..."  é varArgs, significa que pode aceitar nada, um ou mais de um Status, por isso, é uma LISTA !
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}

	public boolean podeAlterarPara(StatusPedido novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}
	
}
