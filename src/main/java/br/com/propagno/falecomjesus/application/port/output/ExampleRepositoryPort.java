package br.com.propagno.falecomjesus.application.port.output;

import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Port de saída (Output Port) - Interface para persistência
 */
public interface ExampleRepositoryPort {
    
    /**
     * Salva uma entidade
     */
    ExampleEntity save(ExampleEntity entity);
    
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

