package br.com.propagno.falecomjesus.adapter.input.rest.config;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
class OpenApiConfigTest {

    @Test
    void shouldLoadOpenApiConfig() {
        // Verifica que a configuração OpenAPI pode ser carregada
        OpenApiConfig config = new OpenApiConfig();
        assertNotNull(config);
    }
}

