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

		// Usar o ModelMapper é justamente fazer todo o trampo comentado abaixo. Ele seta direitinho tudo !
        modelMapper.map(restaurante, restauranteModel);
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
        
        restauranteModel.getCozinha().add(
                algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        
        restauranteModel.getEndereco().getCidade().add(
                algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        
        restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), 
                "formas-pagamento"));
        
        restauranteModel.add(algaLinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"));
        
        return restauranteModel;
    }
    
    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    }   
}

