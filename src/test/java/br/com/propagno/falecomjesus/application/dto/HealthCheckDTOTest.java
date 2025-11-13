package br.com.propagno.falecomjesus.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructor() {
        // When
        HealthCheckDTO dto = new HealthCheckDTO();

        // Then
        assertNotNull(dto);
        assertNull(dto.getStatus());
        assertNull(dto.getService());
        assertNull(dto.getVersion());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        // When
        HealthCheckDTO dto = new HealthCheckDTO("UP", "srv-fale-com-jesus", "1.0.0");

        // Then
        assertNotNull(dto);
        assertEquals("UP", dto.getStatus());
        assertEquals("srv-fale-com-jesus", dto.getService());
        assertEquals("1.0.0", dto.getVersion());
    }

    @Test
    void shouldCreateWithBuilder() {
        // When
        HealthCheckDTO dto = HealthCheckDTO.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();

        // Then
        assertNotNull(dto);
        assertEquals("UP", dto.getStatus());
        assertEquals("srv-fale-com-jesus", dto.getService());
        assertEquals("1.0.0", dto.getVersion());
    }
}

