package com.algaworks.algafood.api.v2.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInputV2 {
	
	@ApiModelProperty(example = "Rio Claro", required = true)
	@NotBlank
	private String nomeCidade;
	
	@Valid
	@NotNull
	private Long idEstado;

}
