package br.com.propagno.falecomjesus.adapter.input.rest.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExampleResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructor() {
        // When
        ExampleResponse response = new ExampleResponse();

        // Then
        assertNotNull(response);
        assertNull(response.getId());
        assertNull(response.getName());
        assertNull(response.getDescription());
        assertNull(response.getCreatedAt());
        assertNull(response.getUpdatedAt());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        ExampleResponse response = new ExampleResponse(1L, "Test Name", "Test Description", now, now);

        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Name", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertEquals(now, response.getCreatedAt());
        assertEquals(now, response.getUpdatedAt());
    }

    @Test
    void shouldCreateWithBuilder() {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        ExampleResponse response = ExampleResponse.builder()
                .id(1L)
                .name("Test Name")
                .description("Test Description")
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Name", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertEquals(now, response.getCreatedAt());
        assertEquals(now, response.getUpdatedAt());
    }

    @Test
    void shouldSetAndGetAllProperties() {
        // Given
        ExampleResponse response = new ExampleResponse();
        LocalDateTime now = LocalDateTime.now();

        // When
        response.setId(2L);
        response.setName("Updated Name");
        response.setDescription("Updated Description");
        response.setCreatedAt(now);
        response.setUpdatedAt(now);

        // Then
        assertEquals(2L, response.getId());
        assertEquals("Updated Name", response.getName());
        assertEquals("Updated Description", response.getDescription());
        assertEquals(now, response.getCreatedAt());
        assertEquals(now, response.getUpdatedAt());
    }

    @Test
    void shouldCreateWithBuilderPartial() {
        // When
        ExampleResponse response = ExampleResponse.builder()
                .id(3L)
                .name("Partial")
                .build();

        // Then
        assertNotNull(response);
        assertEquals(3L, response.getId());
        assertEquals("Partial", response.getName());
        assertNull(response.getDescription());
        assertNull(response.getCreatedAt());
        assertNull(response.getUpdatedAt());
    }
}

