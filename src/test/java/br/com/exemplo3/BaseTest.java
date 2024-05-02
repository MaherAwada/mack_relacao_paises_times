package br.com.exemplo3;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class BaseTest {

	protected String AUTH_TOKEN = "";
	
	@LocalServerPort
	int localServerPort;

	@BeforeEach
	void consgRestAssured() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = localServerPort;
	}
	
	protected RequestSpecification given() {
		return RestAssured.given()
			.header("Authorization", getToken())
			.contentType(ContentType.JSON);
	}
	
	protected RequestSpecification givenNoAuth() {
		return RestAssured.given()
			.contentType(ContentType.JSON);
	}
	
	protected Response get(String url) {
		return given().get(url);
	}
	
	protected Response delete(String url) {
		return given().delete(url);
	}
	
	private String getToken() {
		return List.of(AUTH_TOKEN, Exemplo3Application.ADMIN_TOKEN).stream()
			.filter(this::isNotBlank)
			.map("Bearer %s"::formatted)
			.findFirst().get();
	}
	
	private boolean isNotBlank(String value) {
		return !value.isBlank();
	}

}
