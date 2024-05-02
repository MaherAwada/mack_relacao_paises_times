package br.com.exemplo3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.exemplo3.auth.AuthService;
import br.com.exemplo3.auth.RegisterRequest;

@SpringBootApplication
public class Exemplo3Application {
	
	public static String ADMIN_TOKEN;

	public static void main(String[] args) {
		SpringApplication.run(Exemplo3Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthService service
	) {
		return args -> {
			var admin = new RegisterRequest()
					.setEmail("admin")
					.setPassword("admin");
			ADMIN_TOKEN = service.register(admin).getAccessToken();
			System.out.println("Admin token: " + ADMIN_TOKEN);
		};
	}
	
}

