package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface EstadoControllerOpenApi {


	CollectionModel<EstadoModel> listar();

	EstadoModel buscar(Long estadoId);

	EstadoModel adicionar(EstadoInput estadoInput);

	EstadoModel atualizar(Long estadoId,EstadoInput estadoInput);

	ResponseEntity<Void> remover(Long estadoId);
}        