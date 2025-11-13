package br.com.propagno.falecomjesus.adapter.input.rest.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructor() {
        // When
        ErrorResponse response = new ErrorResponse();

        // Then
        assertNotNull(response);
        assertNull(response.getTimestamp());
        assertNull(response.getStatus());
        assertNull(response.getError());
        assertNull(response.getMessage());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        ErrorResponse response = new ErrorResponse(now, 404, "Not Found", "Entity not found");

        // Then
        assertNotNull(response);
        assertEquals(now, response.getTimestamp());
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getError());
        assertEquals("Entity not found", response.getMessage());
    }

    @Test
    void shouldCreateWithBuilder() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Not Found")
                .message("Entity not found")
                .build();

        // Then
        assertNotNull(response);
        assertEquals(now, response.getTimestamp());
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getError());
        assertEquals("Entity not found", response.getMessage());
    }
}

