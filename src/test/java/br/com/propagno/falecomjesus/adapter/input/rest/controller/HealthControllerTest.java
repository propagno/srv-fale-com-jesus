package br.com.propagno.falecomjesus.adapter.input.rest.controller;

import br.com.propagno.falecomjesus.adapter.input.rest.dto.HealthCheckResponse;
import br.com.propagno.falecomjesus.application.dto.HealthCheckDTO;
import br.com.propagno.falecomjesus.application.port.input.HealthCheckUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HealthControllerTest {

    @Mock
    private HealthCheckUseCase healthCheckUseCase;

    @InjectMocks
    private HealthController healthController;

    private HealthCheckDTO healthCheckDTO;

    @BeforeEach
    void setUp() {
        healthCheckDTO = HealthCheckDTO.builder()
                .status("UP")
                .service("srv-fale-com-jesus")
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    @Test
    void shouldReturnHealthCheckResponse() {
        // Given
        when(healthCheckUseCase.check()).thenReturn(healthCheckDTO);

        // When
        ResponseEntity<HealthCheckResponse> response = healthController.health();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UP", response.getBody().getStatus());
        assertEquals("srv-fale-com-jesus", response.getBody().getService());
        assertEquals("1.0.0-SNAPSHOT", response.getBody().getVersion());
        verify(healthCheckUseCase, times(1)).check();
    }

    @Test
    void shouldCallUseCaseWhenHealthIsCalled() {
        // Given
        when(healthCheckUseCase.check()).thenReturn(healthCheckDTO);

        // When
        healthController.health();

        // Then
        verify(healthCheckUseCase, times(1)).check();
    }
}

