package com.algaworks.algafood.core.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
	
	@Bean
	public OpenAPI openApi() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Algafood API")
						.version("v1")
						.description("REST API do Algafood - by Dynes")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.com")
						)
				).externalDocs(new ExternalDocumentation()
						.description("Documentação da API")
						.url("https://dynes.com")
				);
	}

}
