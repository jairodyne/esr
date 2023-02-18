package com.algaworks.algafood.core.springdoc;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(name = "security_auth", 
	type = SecuritySchemeType.OAUTH2,
	flows = @OAuthFlows(authorizationCode = @OAuthFlow(
			authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
			tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
			scopes = {
					@OAuthScope(name = "READ", description = "read scope"),
					@OAuthScope(name = "WRITE", description = "wirte scope")
					}
			)))
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
				).tags(Arrays.asList(
						new Tag().name("Cidades").description("Gerencia as Cidades springDocConfig")
						));
	}

	
}
