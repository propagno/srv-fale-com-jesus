package br.com.propagno.falecomjesus.application.service;

import br.com.propagno.falecomjesus.application.port.input.ExampleUseCase;
import br.com.propagno.falecomjesus.application.port.output.ExampleRepositoryPort;
import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import br.com.propagno.falecomjesus.domain.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementação dos casos de uso de Example
 */
@Service
@RequiredArgsConstructor
public class ExampleService implements ExampleUseCase {
    
    private final ExampleRepositoryPort repository;
    
    @Override
    @Transactional
    public ExampleEntity create(ExampleEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ExampleEntity> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ExampleEntity> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new EntityNotFoundException("ExampleEntity", id);
        }
        repository.deleteById(id);
    }
}

