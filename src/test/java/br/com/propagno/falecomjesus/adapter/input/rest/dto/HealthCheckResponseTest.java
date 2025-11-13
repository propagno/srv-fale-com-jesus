package br.com.propagno.falecomjesus.adapter.input.rest.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructor() {
        // When
        HealthCheckResponse response = new HealthCheckResponse();

        // Then
        assertNotNull(response);
        assertNull(response.getStatus());
        assertNull(response.getService());
        assertNull(response.getVersion());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        // When
        HealthCheckResponse response = new HealthCheckResponse("UP", "srv-fale-com-jesus", "1.0.0");

        // Then
        assertNotNull(response);
        assertEquals("UP", response.getStatus());
        assertEquals("srv-fale-com-jesus", response.getService());
        assertEquals("1.0.0", response.getVersion());
    }

    @Test
    void shouldCreateWithBuilder() {
        // When
        HealthCheckResponse response = HealthCheckResponse.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();

        // Then
        assertNotNull(response);
        assertEquals("UP", response.getStatus());
        assertEquals("srv-fale-com-jesus", response.getService());
        assertEquals("1.0.0", response.getVersion());
    }

    @Test
    void shouldSetAndGetAllProperties() {
        // Given
        HealthCheckResponse response = new HealthCheckResponse();

        // When
        response.setStatus("DOWN");
        response.setService("test-service");
        response.setVersion("2.0.0");

        // Then
        assertEquals("DOWN", response.getStatus());
        assertEquals("test-service", response.getService());
        assertEquals("2.0.0", response.getVersion());
    }

    @Test
    void shouldCreateWithBuilderPartial() {
        // When
        HealthCheckResponse response = HealthCheckResponse.builder()
                .status("UP")
                .build();

        // Then
        assertNotNull(response);
        assertEquals("UP", response.getStatus());
        assertNull(response.getService());
        assertNull(response.getVersion());
    }
}

