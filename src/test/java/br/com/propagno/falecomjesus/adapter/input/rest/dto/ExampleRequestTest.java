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

    @Test
    void shouldTestEqualsAndHashCode() {
        // Given
        ExampleRequest request1 = ExampleRequest.builder()
                .name("Test")
                .description("Desc")
                .build();
        
        ExampleRequest request2 = ExampleRequest.builder()
                .name("Test")
                .description("Desc")
                .build();
        
        ExampleRequest request3 = ExampleRequest.builder()
                .name("Different")
                .description("Desc")
                .build();
        
        ExampleRequest request4 = ExampleRequest.builder()
                .name("Test")
                .description("Different")
                .build();
        
        ExampleRequest request5 = new ExampleRequest(); // null values

        // Then
        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1, request3);
        assertNotEquals(request1, request4);
        assertNotEquals(request1, request5);
        assertNotEquals(request1, null);
        assertNotEquals(request1, new Object());
        assertEquals(request1, request1); // reflexivo
    }

    @Test
    void shouldTestToString() {
        // Given
        ExampleRequest request = ExampleRequest.builder()
                .name("Test Name")
                .description("Test Description")
                .build();

        // When
        String toString = request.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("Test Name") || toString.contains("name=Test Name"));
    }
}

