package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades")
	public List<CidadeModel> listar();

	@ApiOperation("Busca uma cidade por Id")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID da cidade é inválido", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	public CidadeModel buscar(
			@ApiParam(name = "cidadeId", value = "ID de uma cidade", example = "1") 
			Long cidadeId);

	
	@ApiOperation("Cadastra uma cidade")
	public CidadeModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
			CidadeInput cidadeInput);
	
	
	@ApiOperation("Atualiza uma cidade por Id")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cidade atualizada", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "Cidade não encontrada", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	public CidadeModel atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			Long cidadeId,
			@ApiParam(name = "corpo",  value = "Representação de uma cidade com os novos dados") 
			CidadeInput cidadeInput);


	@ApiOperation("Exclui uma cidade por Id")
	public void remover(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			Long cidadeId);
	
	
}
