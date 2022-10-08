package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
			.antMatchers("/v1/cozinhas/**").permitAll() // aqui permitimos que este endpoint não precisa autenticar
			.anyRequest().authenticated() // os demais, só autenticados.
			
		.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // aqui definimos que não haverá sessions na segurança.
				
		.and()
			.csrf().disable(); // desabilitado csrf. No Oauth2 nem precisa disso.
	}

}
