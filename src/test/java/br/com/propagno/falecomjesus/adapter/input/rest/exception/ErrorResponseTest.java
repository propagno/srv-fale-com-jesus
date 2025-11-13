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
        
        ErrorResponse response4 = ErrorResponse.builder()
                .timestamp(now.plusHours(1))
                .status(404)
                .error("Not Found")
                .message("Entity not found")
                .build();
        
        ErrorResponse response5 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Different Error")
                .message("Entity not found")
                .build();
        
        ErrorResponse response6 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Not Found")
                .message("Different message")
                .build();
        
        ErrorResponse response7 = new ErrorResponse(); // null values

        // Then
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1, response4);
        assertNotEquals(response1, response5);
        assertNotEquals(response1, response6);
        assertNotEquals(response1, response7);
        assertNotEquals(response1, null);
        assertNotEquals(response1, new Object());
        assertEquals(response1, response1); // reflexivo
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

    @Test
    void shouldTestEqualsWithNullFields() {
        // Given - testa diferentes combinações de null
        ErrorResponse response1 = new ErrorResponse();
        ErrorResponse response2 = new ErrorResponse();
        ErrorResponse response3 = ErrorResponse.builder().status(404).build();
        ErrorResponse response4 = ErrorResponse.builder().error("Error").build();
        ErrorResponse response5 = ErrorResponse.builder().message("Message").build();
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse response6 = ErrorResponse.builder().timestamp(now).build();

        // Then
        assertEquals(response1, response2); // ambos null
        assertNotEquals(response1, response3); // um null, outro não
        assertNotEquals(response1, response4);
        assertNotEquals(response1, response5);
        assertNotEquals(response1, response6);
        assertNotEquals(response3, response4);
        assertNotEquals(response3, response5);
        assertNotEquals(response3, response6);
    }

    @Test
    void shouldTestEqualsWithPartialNulls() {
        // Given - testa quando alguns campos são null
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse response1 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error(null)
                .message(null)
                .build();
        
        ErrorResponse response2 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error(null)
                .message(null)
                .build();
        
        ErrorResponse response3 = ErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Error")
                .message(null)
                .build();

        // Then
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }
}

