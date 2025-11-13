package br.com.propagno.falecomjesus.adapter.input.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração do OpenAPI/Swagger para documentação da API REST
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fale com Jesus API")
                        .version("1.0.0")
                        .description("API REST do microsserviço Fale com Jesus - Documentação Swagger/OpenAPI")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev@propagno.com.br"))
                        .license(new License()
                                .name("Proprietary")
                                .url("https://propagno.com.br")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento Local"),
                        new Server()
                                .url("https://api-dev.propagno.com.br")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("https://api.propagno.com.br")
                                .description("Servidor de Produção")));
    }
}

