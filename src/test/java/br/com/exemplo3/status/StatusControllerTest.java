package br.com.exemplo3.status;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import br.com.exemplo3.BaseTest;
import io.restassured.RestAssured;


class StatusControllerTest extends BaseTest {

	@Test
	void test() {
		RestAssured.get(StatusController.URL+"/ping").then().statusCode(200).body(is("pong"));
	}

}
