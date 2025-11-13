package br.com.propagno.falecomjesus.infrastructure.persistence.mapper;

import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import br.com.propagno.falecomjesus.infrastructure.persistence.entity.ExampleEntityJpa;
import org.springframework.stereotype.Component;

/**
 * Mapper entre entidade de domínio e entidade JPA
 */
@Component
public class ExampleEntityMapper {
    
    public ExampleEntityJpa toJpa(ExampleEntity domain) {
        if (domain == null) {
            return null;
        }
        
        ExampleEntityJpa jpa = new ExampleEntityJpa();
        jpa.setId(domain.getId());
        jpa.setName(domain.getName());
        jpa.setDescription(domain.getDescription());
        
        return jpa;
    }
    
    public ExampleEntity toDomain(ExampleEntityJpa jpa) {
        if (jpa == null) {
            return null;
        }
        
        ExampleEntity domain = new ExampleEntity();
        domain.setId(jpa.getId());
        domain.setName(jpa.getName());
        domain.setDescription(jpa.getDescription());
        domain.setCreatedAt(jpa.getCreatedAt());
        domain.setUpdatedAt(jpa.getUpdatedAt());
        
        return domain;
    }
}

