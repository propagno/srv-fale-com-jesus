package br.com.propagno.falecomjesus.adapter.input.rest.controller;

import br.com.propagno.falecomjesus.adapter.input.rest.dto.HealthCheckResponse;
import br.com.propagno.falecomjesus.application.port.input.HealthCheckUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Adapter de entrada REST - Health Check Controller
 */
@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthController {
    
    private final HealthCheckUseCase healthCheckUseCase;
    
    @GetMapping
    public ResponseEntity<HealthCheckResponse> health() {
        var dto = healthCheckUseCase.check();
        var response = HealthCheckResponse.builder()
                .status(dto.getStatus())
                .service(dto.getService())
                .version(dto.getVersion())
                .build();
        
        return ResponseEntity.ok(response);
    }
}

