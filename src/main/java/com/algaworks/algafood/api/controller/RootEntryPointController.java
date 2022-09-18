package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
	
	@Autowired
	private AlgaLinks algalinks;
	
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
	
		rootEntryPointModel.add(algalinks.linkToCozinhas("cozinhas"));
		rootEntryPointModel.add(algalinks.linkToPedidos("pedidos"));
		rootEntryPointModel.add(algalinks.linkToRestaurantes("restaurantes"));
		rootEntryPointModel.add(algalinks.linkToGrupos("grupos"));
		rootEntryPointModel.add(algalinks.linkToUsuarios("usuarios"));
		rootEntryPointModel.add(algalinks.linkToPermissoes("permissoes"));
		rootEntryPointModel.add(algalinks.linkToFormasPagamento("formas-pagamento"));
		rootEntryPointModel.add(algalinks.linkToEstados("estados"));
		rootEntryPointModel.add(algalinks.linkToCidades("cidades"));
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
		
	}

}
