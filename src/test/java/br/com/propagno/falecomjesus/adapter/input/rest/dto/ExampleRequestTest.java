package br.com.propagno.falecomjesus.adapter.input.rest.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructor() {
        // When
        ExampleRequest request = new ExampleRequest();

        // Then
        assertNotNull(request);
        assertNull(request.getName());
        assertNull(request.getDescription());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        // When
        ExampleRequest request = new ExampleRequest("Test Name", "Test Description");

        // Then
        assertNotNull(request);
        assertEquals("Test Name", request.getName());
        assertEquals("Test Description", request.getDescription());
    }

    @Test
    void shouldCreateWithBuilder() {
        // When
        ExampleRequest request = ExampleRequest.builder()
                .name("Test Name")
                .description("Test Description")
                .build();

        // Then
        assertNotNull(request);
        assertEquals("Test Name", request.getName());
        assertEquals("Test Description", request.getDescription());
    }

    @Test
    void shouldSetAndGetProperties() {
        // Given
        ExampleRequest request = new ExampleRequest();

        // When
        request.setName("Test Name");
        request.setDescription("Test Description");

        // Then
        assertEquals("Test Name", request.getName());
        assertEquals("Test Description", request.getDescription());
    }

    @Test
    void shouldCreateWithBuilderPartial() {
        // When
        ExampleRequest request = ExampleRequest.builder()
                .name("Only Name")
                .build();

        // Then
        assertNotNull(request);
        assertEquals("Only Name", request.getName());
        assertNull(request.getDescription());
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        ExampleRequest request = new ExampleRequest();

        // When
        request.setName(null);
        request.setDescription(null);

        // Then
        assertNull(request.getName());
        assertNull(request.getDescription());
    }
}

