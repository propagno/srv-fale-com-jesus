package br.com.propagno.falecomjesus.adapter.input.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

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
        assertNotNull(api.getInfo().getContact());
        assertEquals("Equipe de Desenvolvimento", api.getInfo().getContact().getName());
        assertEquals("dev@propagno.com.br", api.getInfo().getContact().getEmail());
        assertNotNull(api.getInfo().getLicense());
        assertEquals("Proprietary", api.getInfo().getLicense().getName());
        assertNotNull(api.getServers());
        assertEquals(3, api.getServers().size());
    }
}

