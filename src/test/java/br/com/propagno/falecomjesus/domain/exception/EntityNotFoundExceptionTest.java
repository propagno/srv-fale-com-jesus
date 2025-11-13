package br.com.propagno.falecomjesus.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        // Given
        String message = "Entity not found";

        // When
        EntityNotFoundException exception = new EntityNotFoundException(message);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof DomainException);
    }

    @Test
    void shouldCreateExceptionWithEntityNameAndId() {
        // Given
        String entityName = "ExampleEntity";
        Long id = 1L;

        // When
        EntityNotFoundException exception = new EntityNotFoundException(entityName, id);

        // Then
        assertNotNull(exception);
        assertEquals("ExampleEntity com id 1 não encontrado", exception.getMessage());
        assertTrue(exception instanceof DomainException);
    }
}

