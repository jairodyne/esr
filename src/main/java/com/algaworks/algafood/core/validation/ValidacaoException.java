package com.algaworks.algafood.core.validation;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor  // é o construtor da classe criado pelo lombok
@Getter
public class ValidacaoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5608260579252548306L;
	
	private BindingResult bindingResult;

}
