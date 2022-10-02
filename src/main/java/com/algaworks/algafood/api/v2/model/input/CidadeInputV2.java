package com.algaworks.algafood.api.v2.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CidadeInput")
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
