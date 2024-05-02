package br.com.exemplo3.config;
/**
 * Configuração de segurança para a aplicação Spring Boot.
 * Define URLs públicas e restrições de segurança para endpoints.
 * Adiciona um filtro de autenticação JWT à cadeia de filtros de segurança.
 */
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
    info = @Info(
        title = "OpenApi documentation for Exemplo 3",
        version = "1.0"
    ),
    servers = {
        @Server(url = "http://localhost:8080/"),
        @Server(url = "https://exemplo3.lereme.com.br/")
    },
    security = {
        @SecurityRequirement(name = "bearerAuth")
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
