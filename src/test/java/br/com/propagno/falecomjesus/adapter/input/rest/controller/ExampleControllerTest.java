package br.com.propagno.falecomjesus.adapter.input.rest.controller;

import br.com.propagno.falecomjesus.adapter.input.rest.dto.ExampleRequest;
import br.com.propagno.falecomjesus.adapter.input.rest.dto.ExampleResponse;
import br.com.propagno.falecomjesus.application.port.input.ExampleUseCase;
import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExampleControllerTest {

    @Mock
    private ExampleUseCase exampleUseCase;

    @InjectMocks
    private ExampleController exampleController;

    private ExampleEntity exampleEntity;

    @BeforeEach
    void setUp() {
        exampleEntity = new ExampleEntity("Test Name", "Test Description");
        exampleEntity.setId(1L);
        exampleEntity.setCreatedAt(LocalDateTime.now());
        exampleEntity.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void shouldCreateExample() {
        // Given
        ExampleRequest request = new ExampleRequest("Test Name", "Test Description");
        when(exampleUseCase.create(any(ExampleEntity.class))).thenReturn(exampleEntity);

        // When
        ResponseEntity<ExampleResponse> response = exampleController.create(request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Name", response.getBody().getName());
        assertEquals("Test Description", response.getBody().getDescription());
        verify(exampleUseCase, times(1)).create(any(ExampleEntity.class));
    }

    @Test
    void shouldFindExampleById() {
        // Given
        when(exampleUseCase.findById(1L)).thenReturn(Optional.of(exampleEntity));

        // When
        ResponseEntity<ExampleResponse> response = exampleController.findById(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(exampleUseCase, times(1)).findById(1L);
    }

    @Test
    void shouldReturnNotFoundWhenExampleNotFound() {
        // Given
        when(exampleUseCase.findById(999L)).thenReturn(Optional.empty());

        // When
        ResponseEntity<ExampleResponse> response = exampleController.findById(999L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(exampleUseCase, times(1)).findById(999L);
    }

    @Test
    void shouldFindAllExamples() {
        // Given
        ExampleEntity entity2 = new ExampleEntity("Name 2", "Description 2");
        entity2.setId(2L);
        List<ExampleEntity> entities = Arrays.asList(exampleEntity, entity2);
        when(exampleUseCase.findAll()).thenReturn(entities);

        // When
        ResponseEntity<List<ExampleResponse>> response = exampleController.findAll();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(exampleUseCase, times(1)).findAll();
    }

    @Test
    void shouldDeleteExample() {
        // Given
        doNothing().when(exampleUseCase).deleteById(1L);

        // When
        ResponseEntity<Void> response = exampleController.delete(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(exampleUseCase, times(1)).deleteById(1L);
    }
}

