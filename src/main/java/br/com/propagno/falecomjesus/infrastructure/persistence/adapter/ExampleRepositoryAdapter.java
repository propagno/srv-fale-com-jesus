package br.com.propagno.falecomjesus.infrastructure.persistence.adapter;

import br.com.propagno.falecomjesus.application.port.output.ExampleRepositoryPort;
import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import br.com.propagno.falecomjesus.infrastructure.persistence.entity.ExampleEntityJpa;
import br.com.propagno.falecomjesus.infrastructure.persistence.mapper.ExampleEntityMapper;
import br.com.propagno.falecomjesus.infrastructure.persistence.repository.ExampleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter de Persistência - Implementa o Port de saída
 */
@Component
@RequiredArgsConstructor
public class ExampleRepositoryAdapter implements ExampleRepositoryPort {
    
    private final ExampleJpaRepository jpaRepository;
    private final ExampleEntityMapper mapper;
    
    @Override
    public ExampleEntity save(ExampleEntity entity) {
        ExampleEntityJpa jpaEntity = mapper.toJpa(entity);
        ExampleEntityJpa saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }
    
    @Override
    public Optional<ExampleEntity> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<ExampleEntity> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

