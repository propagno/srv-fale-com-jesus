package br.com.propagno.falecomjesus.application.port.input;

import br.com.propagno.falecomjesus.application.dto.HealthCheckDTO;

/**
 * Port de entrada (Input Port) - Caso de uso de Health Check
 */
public interface HealthCheckUseCase {
    
    /**
     * Verifica a saúde da aplicação
     * @return DTO com informações de saúde
     */
    HealthCheckDTO check();
}

