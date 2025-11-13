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
        // Status é int (primitivo), então valor padrão é 0
        assertEquals(0, response.getStatus());
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

    @Test
    void shouldCreateWithBuilderPartial() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When - testa builder com alguns campos
        ErrorResponse response = ErrorResponse.builder()
                .status(500)
                .error("Internal Error")
                .build();

        // Then
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal Error", response.getError());
    }

    @Test
    void shouldSetAndGetAllProperties() {
        // Given
        ErrorResponse response = new ErrorResponse();
        LocalDateTime now = LocalDateTime.now();

        // When
        response.setTimestamp(now);
        response.setStatus(400);
        response.setError("Bad Request");
        response.setMessage("Invalid input");

        // Then
        assertEquals(now, response.getTimestamp());
        assertEquals(400, response.getStatus());
        assertEquals("Bad Request", response.getError());
        assertEquals("Invalid input", response.getMessage());
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse response1 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Not Found")
                .message("Entity not found")
                .build();
        
        ErrorResponse response2 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Not Found")
                .message("Entity not found")
                .build();
        
        ErrorResponse response3 = ErrorResponse.builder()
                .timestamp(now)
                .status(500)
                .error("Not Found")
                .message("Entity not found")
                .build();

        // Then
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1, null);
        assertNotEquals(response1, new Object());
    }

    @Test
    void shouldTestToString() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Not Found")
                .message("Entity not found")
                .build();

        // When
        String toString = response.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("404") || toString.contains("status=404"));
    }
}

