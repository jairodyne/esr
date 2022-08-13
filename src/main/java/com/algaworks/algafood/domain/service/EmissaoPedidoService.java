package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {
	
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    
    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;
    
    @Autowired
    private CadastroCidadeService cadastroCidadeService;
    
    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;
    
    @Autowired
    private CadastroProdutoService cadastroProdutoService;
    
    public Pedido buscarOuFalhar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido)
            .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }
    
    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }
    
    
    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);
        
        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    
    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProdutoService.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());
            
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }
    //=========================================
    
//    @Transactional
//    public Pedido salvar(Pedido pedido) {   // não utilizado, só tentativa. vide emitir()
    	
/*    	
    	{
    	    "restaurante": {
    	        "id" : 1
    	    },
    	    "formaPagamento": {
    	        "id" : 1
    	    },
    	    "enderecoEntrega": {
    	        "cep": "38400-999",
    	        "logradouro": "Rua Paulo Antonio",
    	        "numero": "26969",
    	        "complemento": "Apartamento dos fundos",
    	        "bairro": "Centro",
    	        "cidade": {
    	            "id": 1
    	        }
    	    },
    	    "itens": [
    	        {
    	            "produtoId": 1,
    	            "quantidade": 3,
    	            "observacao": "Sem pimenta"
    	        },
    	        {
    	            "produtoId": 2,
    	            "quantidade": 1
    	        }
    	    ]
    	}    	
*/
//    	Long restauranteId = pedido.getRestaurante().getId();     não precisou nada disso de ID
//    	Long formaPagamentoId = pedido.getFormaPagamento().getId();
//    	Long cidadeId = pedido.getEnderecoEntrega().getCidade().getId();
//    	Long clienteId = 1L;
//    	
//    	Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
//    	FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
//    	Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
//    	Usuario cliente = cadastroUsuarioService.buscarOuFalhar(clienteId);
//    	
//    	pedido.setRestaurante(restaurante);
//    	pedido.setFormaPagamento(formaPagamento);
//    	pedido.getEnderecoEntrega().setCidade(cidade);
//    	
//    	pedido.setCliente(cliente);
//    	
////    	o.h.engine.jdbc.spi.SqlExceptionHelper  Column 'sub_total' cannot be null
//    	
//    	return pedidoRepository.save(pedido);
//    }
}
