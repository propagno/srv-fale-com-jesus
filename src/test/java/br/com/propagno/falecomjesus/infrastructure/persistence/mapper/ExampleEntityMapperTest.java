package br.com.propagno.falecomjesus.infrastructure.persistence.mapper;

import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import br.com.propagno.falecomjesus.infrastructure.persistence.entity.ExampleEntityJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExampleEntityMapperTest {

    private ExampleEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ExampleEntityMapper();
    }

    @Test
    void shouldMapDomainToJpa() {
        // Given
        ExampleEntity domain = new ExampleEntity("Test Name", "Test Description");
        domain.setId(1L);
        domain.setCreatedAt(LocalDateTime.now());
        domain.setUpdatedAt(LocalDateTime.now());

        // When
        ExampleEntityJpa jpa = mapper.toJpa(domain);

        // Then
        assertNotNull(jpa);
        assertEquals(1L, jpa.getId());
        assertEquals("Test Name", jpa.getName());
        assertEquals("Test Description", jpa.getDescription());
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        // When
        ExampleEntityJpa jpa = mapper.toJpa(null);

        // Then
        assertNull(jpa);
    }

    @Test
    void shouldMapJpaToDomain() {
        // Given
        ExampleEntityJpa jpa = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test Name")
                .description("Test Description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // When
        ExampleEntity domain = mapper.toDomain(jpa);

        // Then
        assertNotNull(domain);
        assertEquals(1L, domain.getId());
        assertEquals("Test Name", domain.getName());
        assertEquals("Test Description", domain.getDescription());
        assertNotNull(domain.getCreatedAt());
        assertNotNull(domain.getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenJpaIsNull() {
        // When
        ExampleEntity domain = mapper.toDomain(null);

        // Then
        assertNull(domain);
    }
}

