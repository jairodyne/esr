package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;


@Component
public class RestauranteModelAssembler 
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }
    
    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);
        // Usar o ModelMapper é justamente fazer todo o trampo comentado abaixo. Ele seta direitinho tudo !
//		CozinhaModel cozinhaModel = new CozinhaModel();
//		cozinhaModel.setId(restaurante.getCozinha().getId());
//		cozinhaModel.setNome(restaurante.getCozinha().getNome());
//		
//		RestauranteModel restauranteModel = new RestauranteModel();
//		restauranteModel.setId(restaurante.getId());
//		restauranteModel.setNome(restaurante.getNome());
//		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
//		restauranteModel.setCozinha(cozinhaModel);
//		return restauranteModel;
        
        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        
        if (restaurante.ativacaoPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToAtivarRestaurante(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToInativarRestaurante(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToAbrirRestaurante(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteModel.add(
                    algaLinks.linkToFecharRestaurante(restaurante.getId(), "fechar"));
        }
        
        restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
        
        restauranteModel.getCozinha().add(
                algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        
        if (restauranteModel.getEndereco() != null 
                && restauranteModel.getEndereco().getCidade() != null) {
            restauranteModel.getEndereco().getCidade().add(
                    algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }
        
        restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), 
                "formas-pagamento"));
        
        restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), 
                "responsaveis"));
        
        return restauranteModel;
    }
    
    
    
    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    }   
}

