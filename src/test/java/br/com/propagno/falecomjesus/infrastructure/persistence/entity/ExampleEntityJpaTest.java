package br.com.propagno.falecomjesus.infrastructure.persistence.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExampleEntityJpaTest {

    private ExampleEntityJpa entity;

    @BeforeEach
    void setUp() {
        entity = new ExampleEntityJpa();
    }

    @Test
    void shouldCreateEntityWithBuilder() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        ExampleEntityJpa built = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test Name")
                .description("Test Description")
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Then
        assertNotNull(built);
        assertEquals(1L, built.getId());
        assertEquals("Test Name", built.getName());
        assertEquals("Test Description", built.getDescription());
        assertEquals(now, built.getCreatedAt());
        assertEquals(now, built.getUpdatedAt());
    }

    @Test
    void shouldSetTimestampsOnCreate() {
        // Given
        ExampleEntityJpa entity = new ExampleEntityJpa();
        entity.setName("Test Name");
        // Garante que os timestamps estão null antes
        assertNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedAt());

        // When
        entity.onCreate();

        // Then
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
        // Verifica que os timestamps são iguais (com tolerância para diferenças de nanosegundos)
        // Compara apenas segundos e minutos, ignorando nanosegundos
        assertEquals(entity.getCreatedAt().truncatedTo(java.time.temporal.ChronoUnit.SECONDS),
                entity.getUpdatedAt().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
    }

    @Test
    void shouldSetTimestampsOnCreateWhenAlreadySet() {
        // Given
        ExampleEntityJpa entity = new ExampleEntityJpa();
        LocalDateTime existingTime = LocalDateTime.now().minusDays(1);
        entity.setCreatedAt(existingTime);
        entity.setUpdatedAt(existingTime);

        // When
        entity.onCreate();

        // Then
        // onCreate deve sobrescrever os timestamps existentes
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
        assertTrue(entity.getCreatedAt().isAfter(existingTime));
        assertTrue(entity.getUpdatedAt().isAfter(existingTime));
    }

    @Test
    void shouldUpdateTimestampOnUpdate() {
        // Given
        ExampleEntityJpa entity = new ExampleEntityJpa();
        LocalDateTime createdAt = LocalDateTime.now().minusHours(1);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(createdAt);

        // When
        entity.onUpdate();

        // Then
        assertNotNull(entity.getUpdatedAt());
        assertTrue(entity.getUpdatedAt().isAfter(createdAt));
        assertEquals(createdAt, entity.getCreatedAt());
    }

    @Test
    void shouldSetAndGetProperties() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        entity.setId(1L);
        entity.setName("Test Name");
        entity.setDescription("Test Description");
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);

        // Then
        assertEquals(1L, entity.getId());
        assertEquals("Test Name", entity.getName());
        assertEquals("Test Description", entity.getDescription());
        assertEquals(now, entity.getCreatedAt());
        assertEquals(now, entity.getUpdatedAt());
    }
}

