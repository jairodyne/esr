package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		
//		if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
//			throw new NegocioException(
//					String.format("Status do Pedido %d não pode ser alterado de %s para %s", 
//							pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
//		}
//		pedido.setStatus(StatusPedido.CONFIRMADO);
//		pedido.setDataConfirmacao(OffsetDateTime.now());
		
		pedido.confirmar();  // este método substitui todas linhas comentadas acima !!!
		/**
		 * Necessário injetar PedidoRepository (que seja do Spring Data), porque o Spring Data, para disparar o event
		 * (que está em pedido.confirmar() precisa ser ativado pelo método pedidoRepository.save(pedido) .
		 */
		pedidoRepository.save(pedido);
	}

	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

		pedido.cancelar();
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

		pedido.entregar();
	}
	
}
