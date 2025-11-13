package br.com.propagno.falecomjesus.application.port.input;

import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Port de entrada (Input Port) - Casos de uso de Example
 */
public interface ExampleUseCase {
    
    /**
     * Cria uma nova entidade
     */
    ExampleEntity create(ExampleEntity entity);
    
    /**
     * Busca por ID
     */
    Optional<ExampleEntity> findById(Long id);
    
    /**
     * Lista todas as entidades
     */
    List<ExampleEntity> findAll();
    
    /**
     * Remove uma entidade
     */
    void deleteById(Long id);
}

