package br.com.exemplo3.utils;
/**
 * Classe principal que inicia a aplicação Spring Boot.
 * Realiza a inicialização e configuração da aplicação.
 * Cria um usuário "admin" durante a inicialização da aplicação.
 */
import java.net.URI;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {

	public static URI createLocation(String url, UUID id) {
		var vars = new StringSubstitutor(Map.of(
        	"url", url,
        	"id", id.toString()
		));
        return URI.create(vars.replace("${url}/${id}"));
	}
	
	public static <T> ResponseEntity<T> statusNoContent(Object obj) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	public static <T> ResponseEntity<T> statusNotFound() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public static <T> ResponseEntity<T> statusCreated(URI uri) {
		return ResponseEntity.created(uri).build();
	}
	
}
