package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as Cidades")
	CollectionModel<CidadeModel> listar();

	@Operation(summary = "Busca uma Cidade por Id")
	CidadeModel buscar(@Parameter(description = "ID de uma Cidade", example = "1", required = true) Long cidadeId);

	@Operation(summary = "Cadastra uma Cidade", description = "Cadastro de uma Cidade, necessita de um Estado e um nome válido.")
	CidadeModel adicionar(@RequestBody(description = "Representação de uma nova Cidade", required = true)  CidadeInput cidadeInput);
	
	@Operation(summary = "Atualiza uma Cidade por Id")
	CidadeModel atualizar(@Parameter(description = "ID de uma Cidade", example = "1", required = true) Long cidadeId, 
			@RequestBody(description = "Representação de uma Cidade com dados atualizados.", required = true)  CidadeInput cidadeInput);

	@Operation(summary = "Exclui uma Cidade por Id")
	ResponseEntity<Void> remover(@Parameter(description = "ID de uma Cidade", example = "1", required = true) Long cidadeId);
	
}
