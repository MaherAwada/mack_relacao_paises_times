package br.com.exemplo3.time;

import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.exemplo3.BaseTest;

class TimeControllerTest extends BaseTest {

	private static String TIME_LOCATION;
	
	@Test
	@Order(1)
	void notFound() {
		var id = UUID.randomUUID();
		var req = new TimeRequest().setNome("teste");
		given().body(req).put(TimeController.URL+"/"+id.toString()).then().statusCode(404);
		given().delete(TimeController.URL+"/"+id.toString()).then().statusCode(404);
	}
	
	@Test
	@Order(1)
	void verificaSeEstaVazio() {
		get(TimeController.URL).then()
			.statusCode(200)
			.body(is("[]"));
	}
	
	@Test
	@Order(2)
	void criaTime() {
		
		var req = new TimeRequest()
			.setNome("Teste1")
			.setAnoDeFundacao(2000)
			.setCidade("Cidade1")
			.setEstado("Estado1");
		
		TIME_LOCATION = given().body(req).post(TimeController.URL).then()
			.statusCode(201)
			.extract()
				.header("location");
		
		get(TIME_LOCATION).then()
			.statusCode(200)
			.body("nome", is(req.getNome()))
			.body("anoDeFundacao", is(req.getAnoDeFundacao()))
			.body("cidade", is(req.getCidade()))
			.body("estado", is(req.getEstado()));
		
	}
	
	@Test
	@Order(3)
	void alteraTime() {
		
		var req = new TimeRequest()
			.setNome("Teste2")
			.setAnoDeFundacao(2002)
			.setCidade("Cidade2")
			.setEstado("Estado2");
		
		given().body(req).put(TIME_LOCATION).then().statusCode(204);
		
		get(TIME_LOCATION).then()
			.statusCode(200)
			.body("nome", is(req.getNome()))
			.body("anoDeFundacao", is(req.getAnoDeFundacao()))
			.body("cidade", is(req.getCidade()))
			.body("estado", is(req.getEstado()));
		
	}
	
	@Test
	@Order(4)
	void deleteTime() {
		delete(TIME_LOCATION).then().statusCode(204);
		get(TIME_LOCATION).then().statusCode(404);
	}

	@Test
	@Order(5)
	void pesquisaPorTermo() {
		
		List.of(
			new TimeRequest()
				.setNome("Nome1")
				.setAnoDeFundacao(2001)
				.setEstado("Estado1")
				.setCidade("Cidade1"),
			new TimeRequest()
				.setNome("Nome2")
				.setAnoDeFundacao(2002)
				.setEstado("Estado2")
				.setCidade("Cidade2"),
			new TimeRequest()
				.setNome("Oto")
				.setAnoDeFundacao(2003)
				.setEstado("Estado2")
				.setCidade("Cidade1"))
			.forEach(req -> {
				given().body(req).post(TimeController.URL).then()
					.statusCode(201);
			});
		
		get(TimeController.URL+"?nome=Nome1").then()
			.statusCode(200)
			.body("size()", is(1));
			
		get(TimeController.URL+"?anoDeFundacao=2002").then()
			.statusCode(200)
			.body("size()", is(1));
		
		get(TimeController.URL+"?estado=Estado2").then()
			.statusCode(200)
			.body("size()", is(2));
		
		get(TimeController.URL+"?cidade=Cidade1").then()
			.statusCode(200)
			.body("size()", is(2));
		
		get(TimeController.URL+"?nome=ome").then()
			.statusCode(200)
			.body("size()", is(2));
		
		get(TimeController.URL+"?nome=ome&estado=Estado2").then()
			.statusCode(200)
			.body("size()", is(1));
			
	}

}
