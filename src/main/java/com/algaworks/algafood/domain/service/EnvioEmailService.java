package com.algaworks.algafood.domain.service;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface EnvioEmailService {
	
	void enviar(Mensagem mensagem);
	
	@Getter
	@Builder
	class Mensagem {
		
		@Singular
		private Set<String> destinatarios; // usou o Set pra não duplicar o destinatário
		
		@NonNull
		private String assunto;
		
		@NonNull
		private String corpo;
		
		@Singular("variavel")
		private Map<String, Object> variaveis;
		
	}

}
