package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade cidade) {
		// Usar o ModelMapper é justamente fazer todo o trampo comentado abaixo. Ele seta direitinho tudo !
		return modelMapper.map(cidade, CidadeModel.class);
	}


	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream()
				.map(cidade -> toModel(cidade))
				.collect(Collectors.toList());
	}

}
