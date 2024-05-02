package br.com.exemplo3.auth;

import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.exemplo3.BaseTest;
import br.com.exemplo3.pais.PaisController;

class AuthControllerTest extends BaseTest {

	@Test
	@Order(1)
	void register() {
		
		var req = new RegisterRequest()
			.setEmail("teste@gmail.com")
			.setPassword("1234");
	
		var auth = givenNoAuth()
			.body(req)
			.post(AuthController.URL+"/register")
			.then()
				.statusCode(200)
				.body("access_token", notNullValue())
				.body("refresh_token", notNullValue())
			.extract()
				.as(AuthResponse.class);
		
		AUTH_TOKEN = "teste";
		get(PaisController.URL).then().statusCode(403);
		
		AUTH_TOKEN = auth.getAccessToken();
		get(PaisController.URL).then().statusCode(200);
		
	}
	
	@Test
	@Order(2)
	void authenticate() {
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		
		var req = new AuthRequest()
				.setEmail("teste@gmail.com")
				.setPassword("1234");
		
		var auth = givenNoAuth()
				.body(req)
				.post(AuthController.URL+"/authenticate")
				.then()
					.statusCode(200)
					.body("access_token", notNullValue())
					.body("refresh_token", notNullValue())
				.extract()
					.as(AuthResponse.class);
		
		AUTH_TOKEN = "teste";
		get(PaisController.URL).then().statusCode(403);
		
		AUTH_TOKEN = auth.getAccessToken();
		get(PaisController.URL).then().statusCode(200);
		
	}

}
