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

    @Test
    void shouldSetAndGetAllProperties() {
        // Given
        HealthCheckDTO dto = new HealthCheckDTO();

        // When
        dto.setStatus("DOWN");
        dto.setService("test-service");
        dto.setVersion("2.0.0");

        // Then
        assertEquals("DOWN", dto.getStatus());
        assertEquals("test-service", dto.getService());
        assertEquals("2.0.0", dto.getVersion());
    }

    @Test
    void shouldCreateWithBuilderPartial() {
        // When
        HealthCheckDTO dto = HealthCheckDTO.builder()
                .status("UP")
                .build();

        // Then
        assertNotNull(dto);
        assertEquals("UP", dto.getStatus());
        assertNull(dto.getService());
        assertNull(dto.getVersion());
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        // Given
        HealthCheckDTO dto1 = HealthCheckDTO.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();
        
        HealthCheckDTO dto2 = HealthCheckDTO.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();
        
        HealthCheckDTO dto3 = HealthCheckDTO.builder()
                .status("DOWN")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();

        // Then
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
    }

    @Test
    void shouldTestToString() {
        // Given
        HealthCheckDTO dto = HealthCheckDTO.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0")
                .build();

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("UP"));
        assertTrue(toString.contains("srv-fale-com-jesus"));
        assertTrue(toString.contains("1.0.0"));
    }
}

