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

    @Test
    void shouldUpdateTimestampOnUpdateWhenCreatedAtIsNull() {
        // Given
        ExampleEntityJpa entity = new ExampleEntityJpa();
        entity.setName("Test");

        // When
        entity.onUpdate();

        // Then
        assertNotNull(entity.getUpdatedAt());
        assertNull(entity.getCreatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ExampleEntityJpa entity1 = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test")
                .description("Desc")
                .createdAt(now)
                .updatedAt(now)
                .build();
        
        ExampleEntityJpa entity2 = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test")
                .description("Desc")
                .createdAt(now)
                .updatedAt(now)
                .build();
        
        ExampleEntityJpa entity3 = ExampleEntityJpa.builder()
                .id(2L)
                .name("Test")
                .description("Desc")
                .createdAt(now)
                .updatedAt(now)
                .build();
        
        ExampleEntityJpa entity4 = ExampleEntityJpa.builder()
                .id(1L)
                .name("Different")
                .description("Desc")
                .createdAt(now)
                .updatedAt(now)
                .build();
        
        ExampleEntityJpa entity5 = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test")
                .description("Different")
                .createdAt(now)
                .updatedAt(now)
                .build();
        
        ExampleEntityJpa entity6 = new ExampleEntityJpa(); // null values

        // Then
        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
        assertNotEquals(entity1, entity3);
        assertNotEquals(entity1, entity4);
        assertNotEquals(entity1, entity5);
        assertNotEquals(entity1, entity6);
        assertNotEquals(entity1, null);
        assertNotEquals(entity1, new Object());
        assertEquals(entity1, entity1); // reflexivo
    }

    @Test
    void shouldTestToString() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ExampleEntityJpa entity = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test Name")
                .description("Test Description")
                .createdAt(now)
                .updatedAt(now)
                .build();

        // When
        String toString = entity.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("1") || toString.contains("id=1"));
    }
}

