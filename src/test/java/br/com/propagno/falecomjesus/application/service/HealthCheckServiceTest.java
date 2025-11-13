package br.com.propagno.falecomjesus.application.service;

import br.com.propagno.falecomjesus.application.dto.HealthCheckDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "spring.application.name=srv-fale-com-jesus",
        "app.version=1.0.0-SNAPSHOT"
})
class HealthCheckServiceTest {

    @InjectMocks
    private HealthCheckService healthCheckService;

    @BeforeEach
    void setUp() {
        healthCheckService = new HealthCheckService();
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

