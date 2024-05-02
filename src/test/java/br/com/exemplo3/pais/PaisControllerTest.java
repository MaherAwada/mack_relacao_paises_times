package br.com.exemplo3.pais;

import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.exemplo3.BaseTest;

class PaisControllerTest extends BaseTest {

	private static String PAIS_LOCATION;

	@Test
	@Order(1)
	void notFound() {
		var id = UUID.randomUUID();
		var req = new PaisRequest().setNome("teste");
		given().body(req).put(PaisController.URL+"/"+id.toString()).then().statusCode(404);
		given().delete(PaisController.URL+"/"+id.toString()).then().statusCode(404);
	}
	
	@Test
	@Order(1)
	void verificaSeEstaVazio() {
		get(PaisController.URL).then()
			.statusCode(200)
			.body(is("[]"));
	}
	
	@Test
	@Order(2)
	void criaPais() {
		
		var req = new PaisRequest()
			.setNome("Teste1")
			.setContinente("Africa")
			.setPopulacao(1);
		
		PAIS_LOCATION = given().body(req).post(PaisController.URL).then()
			.statusCode(201)
			.extract()
				.header("location");
		
		get(PAIS_LOCATION).then()
			.statusCode(200)
			.body("nome", is(req.getNome()))
			.body("continente", is(req.getContinente()))
			.body("populacao", is(req.getPopulacao()));
		
	}
	
	@Test
	@Order(3)
	void alteraPais() {
		
		var req = new PaisRequest()
			.setNome("Teste2")
			.setContinente("Asia")
			.setPopulacao(2);
		
		given().body(req).put(PAIS_LOCATION).then().statusCode(204);
		
		get(PAIS_LOCATION).then()
			.statusCode(200)
			.body("nome", is(req.getNome()))
			.body("continente", is(req.getContinente()))
			.body("populacao", is(req.getPopulacao()));
		
	}
	
	@Test
	@Order(4)
	void deletePais() {
		delete(PAIS_LOCATION).then().statusCode(204);
		get(PAIS_LOCATION).then().statusCode(404);
	}

	@Test
	@Order(5)
	void pesquisaPorTermo() {
		
		List.of(
			new PaisRequest()
				.setNome("Nome1")
				.setContinente("Continente1")
				.setPopulacao(1),
			new PaisRequest()
				.setNome("Nome2")
				.setContinente("Continente2")
				.setPopulacao(3),
			new PaisRequest()
				.setNome("Oto")
				.setContinente("Continente3")
				.setPopulacao(3))
			.forEach(req -> {
				given().body(req).post(PaisController.URL).then()
					.statusCode(201);
			});
		
		get(PaisController.URL+"?nome=Nome1").then()
			.statusCode(200)
			.body("size()", is(1));
			
		get(PaisController.URL+"?continente=Continente2").then()
			.statusCode(200)
			.body("size()", is(1));
		
		get(PaisController.URL+"?populacao=3").then()
			.statusCode(200)
			.body("size()", is(2));
		
		get(PaisController.URL+"?nome=ome").then()
			.statusCode(200)
			.body("size()", is(2));
		
		get(PaisController.URL+"?nome=ome&populacao=3").then()
			.statusCode(200)
			.body("size()", is(1));
			
	}
	
}
