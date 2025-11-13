package br.com.propagno.falecomjesus.adapter.input.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class OpenApiConfigTest {

    @Autowired(required = false)
    private OpenAPI openAPI;

    @Test
    void shouldLoadOpenApiConfig() {
        // Verifica que a configuração OpenAPI pode ser carregada
        OpenApiConfig config = new OpenApiConfig();
        assertNotNull(config);
    }

    @Test
    void shouldCreateOpenAPIBean() {
        // Verifica que o bean OpenAPI é criado
        OpenApiConfig config = new OpenApiConfig();
        OpenAPI api = config.customOpenAPI();
        assertNotNull(api);
        assertNotNull(api.getInfo());
        assertEquals("Fale com Jesus API", api.getInfo().getTitle());
        assertEquals("1.0.0", api.getInfo().getVersion());
    }
}

