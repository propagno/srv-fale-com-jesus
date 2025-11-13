package br.com.propagno.falecomjesus.application.service;

import br.com.propagno.falecomjesus.application.port.output.ExampleRepositoryPort;
import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import br.com.propagno.falecomjesus.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExampleServiceTest {

    @Mock
    private ExampleRepositoryPort repository;

    @InjectMocks
    private ExampleService exampleService;

    private ExampleEntity exampleEntity;

    @BeforeEach
    void setUp() {
        exampleEntity = new ExampleEntity("Test Name", "Test Description");
        exampleEntity.setId(1L);
    }

    @Test
    void shouldCreateExample() {
        // Given
        when(repository.save(any(ExampleEntity.class))).thenReturn(exampleEntity);

        // When
        ExampleEntity result = exampleService.create(exampleEntity);

        // Then
        assertNotNull(result);
        assertEquals("Test Name", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        verify(repository, times(1)).save(any(ExampleEntity.class));
    }

    @Test
    void shouldFindExampleById() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(exampleEntity));

        // When
        Optional<ExampleEntity> result = exampleService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Test Name", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyWhenExampleNotFound() {
        // Given
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<ExampleEntity> result = exampleService.findById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(999L);
    }

    @Test
    void shouldFindAllExamples() {
        // Given
        ExampleEntity entity2 = new ExampleEntity("Name 2", "Description 2");
        entity2.setId(2L);
        List<ExampleEntity> entities = Arrays.asList(exampleEntity, entity2);
        when(repository.findAll()).thenReturn(entities);

        // When
        List<ExampleEntity> result = exampleService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldDeleteExample() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(exampleEntity));
        doNothing().when(repository).deleteById(1L);

        // When
        exampleService.deleteById(1L);

        // Then
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentExample() {
        // Given
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            exampleService.deleteById(999L);
        });
        verify(repository, times(1)).findById(999L);
        verify(repository, never()).deleteById(anyLong());
    }
}

