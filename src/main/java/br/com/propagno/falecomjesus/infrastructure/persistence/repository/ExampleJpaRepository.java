package br.com.propagno.falecomjesus.infrastructure.persistence.repository;

import br.com.propagno.falecomjesus.infrastructure.persistence.entity.ExampleEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA (Spring Data)
 */
@Repository
public interface ExampleJpaRepository extends JpaRepository<ExampleEntityJpa, Long> {
}

