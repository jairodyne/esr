package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
//	@Autowired
//	private CadastroCozinhaService cadastroCozinhaService;
	
//	@Autowired
//	private Flyway flyway;   // retirado do código. Vamos utilizar outra técnica.
	
	@LocalServerPort
	private int port;
	
	private int quantidadeCozinhas;
	private static final int COZINHA_ID_INEXISTENTE = 100;

	private Cozinha cozinhaAmericana;
	private String jsonCorretoCozinhaChinesa;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	

	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		/**
		 * A cada teste, reinicia o banco aplicando o patch contido em testdata.afterMigrate.sql
		 */
//		flyway.migrate();
		
		databaseCleaner.clearTables();
		prepararDados();
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/deveRetornarStatus201_QuandoCadastrarCozinhas.json");
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
//			.statusCode(200);
			.statusCode(HttpStatus.OK.value());
	}
	
	
	@Test
	public void deveConterTotalDeCozinhas_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(this.quantidadeCozinhas) )
			.body("nome", Matchers.hasItems("Americana", "Tailandesa"));
		
	}
	
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinhas() {
		given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
	private void prepararDados() {
		
		Cozinha cozinhaTailandesa = new Cozinha();
	    cozinhaTailandesa.setNome("Tailandesa");
	    cozinhaRepository.save(cozinhaTailandesa);

	    cozinhaAmericana = new Cozinha();
	    cozinhaAmericana.setNome("Americana");
	    cozinhaRepository.save(cozinhaAmericana);
		
	    Cozinha cozinha3 = new Cozinha();
	    cozinha3.setNome("Italiana");
	    cozinhaRepository.save(cozinha3);
	    
		this.quantidadeCozinhas = (int) cozinhaRepository.count();
		
	}
	
	
	

//--- Testes de Integração (não fazem parte do escopo do curso)	
/*	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Fulera");
		
		// ação
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
		
		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		ConstraintViolationException erroEsperado =
			      Assertions.assertThrows(ConstraintViolationException.class, () -> {
			         cadastroCozinhaService.salvar(novaCozinha);
			      });
			   
			   assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(1L);
		
		EntidadeEmUsoException erroEsperado =
			      Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			         cadastroCozinhaService.excluir(cozinhaAtual.getId());
			      });
			   
			   assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(9991L);
		
		CozinhaNaoEncontradaException erroEsperado =
			      Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
			         cadastroCozinhaService.excluir(999L);
			      });
			   
			   assertThat(erroEsperado).isNotNull();
		
	}
	
*/	

}
