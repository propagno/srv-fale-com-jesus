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

    @Test
    void shouldTestEqualsAndHashCode() {
        // Given
        HealthCheckResponse response1 = HealthCheckResponse.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();
        
        HealthCheckResponse response2 = HealthCheckResponse.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();
        
        HealthCheckResponse response3 = HealthCheckResponse.builder()
                .status("DOWN")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();

        // Then
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1, null);
        assertNotEquals(response1, new Object());
    }

    @Test
    void shouldTestToString() {
        // Given
        HealthCheckResponse response = HealthCheckResponse.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();

        // When
        String toString = response.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("UP") || toString.contains("status=UP"));
    }
}

