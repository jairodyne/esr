package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;


	public Pedido toDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
	public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
		/**
		 * Para evitar Caused by: org.hibernate.HibernateException: identifier of an instance of 
		 * com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
		 */
//		pedido.setFormaPagamento(new FormaPagamento());
//		pedido.setRestaurante(new Restaurante());
//		pedido.setCliente(new Usuario());
//		
//		if(pedido.getEnderecoEntrega() != null) {
//			pedido.getEnderecoEntrega().setCidade(new Cidade());
//		}
		
		modelMapper.map(pedidoInput, pedido);
	}

}
