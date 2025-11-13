package br.com.propagno.falecomjesus.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleEntityTest {

    private ExampleEntity exampleEntity;

    @BeforeEach
    void setUp() {
        exampleEntity = new ExampleEntity();
    }

    @Test
    void shouldCreateEntityWithNoArgsConstructor() {
        // When
        ExampleEntity entity = new ExampleEntity();

        // Then
        assertNotNull(entity);
        assertNull(entity.getName());
        assertNull(entity.getDescription());
    }

    @Test
    void shouldCreateEntityWithAllArgsConstructor() {
        // Given
        String name = "Test Name";
        String description = "Test Description";

        // When
        ExampleEntity entity = new ExampleEntity(name, description);

        // Then
        assertNotNull(entity);
        assertEquals(name, entity.getName());
        assertEquals(description, entity.getDescription());
    }

    @Test
    void shouldSetAndGetName() {
        // Given
        String name = "Test Name";

        // When
        exampleEntity.setName(name);

        // Then
        assertEquals(name, exampleEntity.getName());
    }

    @Test
    void shouldSetAndGetDescription() {
        // Given
        String description = "Test Description";

        // When
        exampleEntity.setDescription(description);

        // Then
        assertEquals(description, exampleEntity.getDescription());
    }

    @Test
    void shouldInheritFromBaseEntity() {
        // Given
        ExampleEntity entity = new ExampleEntity("Name", "Description");
        Long id = 1L;

        // When
        entity.setId(id);

        // Then
        assertEquals(id, entity.getId());
        assertTrue(entity instanceof BaseEntity);
    }
}

