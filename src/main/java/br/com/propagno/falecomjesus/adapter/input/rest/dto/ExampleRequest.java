package br.com.propagno.falecomjesus.adapter.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de requisição REST
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    
    private String description;
}

