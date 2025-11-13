package br.com.propagno.falecomjesus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Health Check
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckDTO {
    
    private String status;
    private String service;
    private String version;
}

