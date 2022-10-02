package com.algaworks.algafood.api.v2.openapi;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades Bacanas !")
public interface CidadeControllerV2OpenApi {

	@ApiOperation("Lista as cidades")
	CollectionModel<CidadeModelV2> listar();

	@ApiOperation("Busca uma cidade por Id")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID da cidade é inválido", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	CidadeModelV2 buscar(
			@ApiParam(name = "cidadeId", value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId);

	
	@ApiOperation("Cadastra uma cidade")
	CidadeModelV2 adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
			CidadeInputV2 cidadeInput);
	
	
	@ApiOperation("Atualiza uma cidade por Id")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cidade atualizada", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "Cidade não encontrada", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	CidadeModelV2 atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId,
			@ApiParam(name = "corpo",  value = "Representação de uma cidade com os novos dados") 
			CidadeInputV2 cidadeInput);


	@ApiOperation("Exclui uma cidade por Id")
	void remover(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId);
	
	
}
