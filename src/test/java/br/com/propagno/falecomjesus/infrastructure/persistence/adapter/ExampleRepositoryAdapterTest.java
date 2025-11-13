package br.com.propagno.falecomjesus.infrastructure.persistence.adapter;

import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import br.com.propagno.falecomjesus.infrastructure.persistence.entity.ExampleEntityJpa;
import br.com.propagno.falecomjesus.infrastructure.persistence.mapper.ExampleEntityMapper;
import br.com.propagno.falecomjesus.infrastructure.persistence.repository.ExampleJpaRepository;
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
class ExampleRepositoryAdapterTest {

    @Mock
    private ExampleJpaRepository jpaRepository;

    @Mock
    private ExampleEntityMapper mapper;

    @InjectMocks
    private ExampleRepositoryAdapter adapter;

    private ExampleEntity domainEntity;
    private ExampleEntityJpa jpaEntity;

    @BeforeEach
    void setUp() {
        domainEntity = new ExampleEntity("Test Name", "Test Description");
        domainEntity.setId(1L);
        domainEntity.setCreatedAt(LocalDateTime.now());
        domainEntity.setUpdatedAt(LocalDateTime.now());

        jpaEntity = ExampleEntityJpa.builder()
                .id(1L)
                .name("Test Name")
                .description("Test Description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldSaveEntity() {
        // Given
        when(mapper.toJpa(domainEntity)).thenReturn(jpaEntity);
        when(jpaRepository.save(jpaEntity)).thenReturn(jpaEntity);
        when(mapper.toDomain(jpaEntity)).thenReturn(domainEntity);

        // When
        ExampleEntity result = adapter.save(domainEntity);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(mapper, times(1)).toJpa(domainEntity);
        verify(jpaRepository, times(1)).save(jpaEntity);
        verify(mapper, times(1)).toDomain(jpaEntity);
    }

    @Test
    void shouldFindEntityById() {
        // Given
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(jpaEntity));
        when(mapper.toDomain(jpaEntity)).thenReturn(domainEntity);

        // When
        Optional<ExampleEntity> result = adapter.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(jpaRepository, times(1)).findById(1L);
        verify(mapper, times(1)).toDomain(jpaEntity);
    }

    @Test
    void shouldReturnEmptyWhenEntityNotFound() {
        // Given
        when(jpaRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<ExampleEntity> result = adapter.findById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(jpaRepository, times(1)).findById(999L);
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void shouldFindAllEntities() {
        // Given
        ExampleEntityJpa jpaEntity2 = ExampleEntityJpa.builder()
                .id(2L)
                .name("Name 2")
                .description("Description 2")
                .build();
        ExampleEntity domainEntity2 = new ExampleEntity("Name 2", "Description 2");
        domainEntity2.setId(2L);

        List<ExampleEntityJpa> jpaEntities = Arrays.asList(jpaEntity, jpaEntity2);
        when(jpaRepository.findAll()).thenReturn(jpaEntities);
        when(mapper.toDomain(jpaEntity)).thenReturn(domainEntity);
        when(mapper.toDomain(jpaEntity2)).thenReturn(domainEntity2);

        // When
        List<ExampleEntity> result = adapter.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(jpaRepository, times(1)).findAll();
        verify(mapper, times(2)).toDomain(any(ExampleEntityJpa.class));
    }

    @Test
    void shouldDeleteEntityById() {
        // Given
        doNothing().when(jpaRepository).deleteById(1L);

        // When
        adapter.deleteById(1L);

        // Then
        verify(jpaRepository, times(1)).deleteById(1L);
    }
}

