package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest().authenticated()
		.and()
		.cors().and()
		.oauth2ResourceServer().jwt();
	}
	
	
	
//	@Bean   Isto é pra chave simétrica. Agora não queremos mais.
//	public JwtDecoder jwtDecoder() {
//		var secretKey = new SecretKeySpec("das0875DURD80759086459764974duiyteduiytrduyuitdeeo".getBytes(), "HmacSHA256");
//		return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
	
}
