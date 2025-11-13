package br.com.propagno.falecomjesus.application.service;

import br.com.propagno.falecomjesus.application.dto.HealthCheckDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckServiceTest {

    private HealthCheckService healthCheckService;

    @BeforeEach
    void setUp() {
        healthCheckService = new HealthCheckService();
        ReflectionTestUtils.setField(healthCheckService, "applicationName", "srv-fale-com-jesus");
        ReflectionTestUtils.setField(healthCheckService, "version", "1.0.0-SNAPSHOT");
    }

    @Test
    void shouldReturnHealthCheckDTOWithUpStatus() {
        // When
        HealthCheckDTO result = healthCheckService.check();

        // Then
        assertNotNull(result);
        assertEquals("UP", result.getStatus());
        assertNotNull(result.getService());
        assertNotNull(result.getVersion());
    }

    @Test
    void shouldReturnCorrectServiceName() {
        // When
        HealthCheckDTO result = healthCheckService.check();

        // Then
        assertEquals("srv-fale-com-jesus", result.getService());
    }

    @Test
    void shouldReturnCorrectVersion() {
        // When
        HealthCheckDTO result = healthCheckService.check();

        // Then
        assertEquals("1.0.0-SNAPSHOT", result.getVersion());
    }
}

