package com.algaworks.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.core.web.AlgaMediaTypes;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeControllerV2 {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;
	
	@GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeModelV2> listar(){
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);
		
	}

	@GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}

	
	@PostMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput){
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			cidade = cadastroCidadeService.salvar(cidade);
			
			CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
			
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);  // mensagem e a causa implementado em NegocioException
		}
	}
	
	
	@PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CidadeModelV2 atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	
//	@PatchMapping("/{cidadeId}")
//	public Cidade atualizarParcial(@PathVariable Long cidadeId, @RequestBody Map<String, Object> campos) {
//		Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
//		merge(campos, cidadeAtual);
//		return atualizar(cidadeId, cidadeAtual);
//	}


//	private void merge(Map<String, Object> dadosOrigem, Cidade cidadeDestino) {
//		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//			ObjectMapper objectMapper = new ObjectMapper();
//			Cidade cidadeOrigem = objectMapper.convertValue(dadosOrigem, Cidade.class);
//			Field field = ReflectionUtils.findField(Cidade.class, nomePropriedade);
//			field.setAccessible(true);
//			
//			Object novoValor = ReflectionUtils.getField(field, cidadeOrigem);
//			ReflectionUtils.setField(field, cidadeDestino, novoValor);
//		});
//	}


//  Não pode ser mapeado na mesma URL em um MediaType diferente, já que não aceita entrada e retorna void.
//	@DeleteMapping(value = "/{cidadeId}", produces = {})
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable Long cidadeId) {
//		cadastroCidade.excluir(cidadeId);	
//	}
	

}
