package br.com.propagno.falecomjesus.application.service;

import br.com.propagno.falecomjesus.application.dto.HealthCheckDTO;
import br.com.propagno.falecomjesus.application.port.input.HealthCheckUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Implementação do caso de uso de Health Check
 */
@Service
public class HealthCheckService implements HealthCheckUseCase {
    
    @Value("${spring.application.name:srv-fale-com-jesus}")
    private String applicationName;
    
    @Value("${app.version:1.0.0-SNAPSHOT}")
    private String version;
    
    @Override
    public HealthCheckDTO check() {
        return HealthCheckDTO.builder()
                .status("UP")
                .service(applicationName)
                .version(version)
                .build();
    }
}

