package br.com.propagno.falecomjesus.adapter.input.rest.exception;

import br.com.propagno.falecomjesus.domain.exception.DomainException;
import br.com.propagno.falecomjesus.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleEntityNotFoundException() {
        // Given
        EntityNotFoundException ex = new EntityNotFoundException("ExampleEntity", 1L);

        // When
        ResponseEntity<ErrorResponse> response = handler.handleEntityNotFound(ex);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertEquals("Entity Not Found", response.getBody().getError());
        assertTrue(response.getBody().getMessage().contains("ExampleEntity"));
    }

    @Test
    void shouldHandleDomainException() {
        // Given
        DomainException ex = new DomainException("Domain error message");

        // When
        ResponseEntity<ErrorResponse> response = handler.handleDomainException(ex);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertEquals("Domain Error", response.getBody().getError());
        assertEquals("Domain error message", response.getBody().getMessage());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        // Given
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("exampleRequest", "name", "Nome é obrigatório");
        List<org.springframework.validation.ObjectError> errors = new ArrayList<>();
        errors.add(fieldError);

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(errors);

        // When
        ResponseEntity<Map<String, Object>> response = handler.handleValidationExceptions(ex);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().get("status"));
        assertEquals("Validation Error", response.getBody().get("error"));
        assertTrue(response.getBody().containsKey("errors"));
    }

    @Test
    void shouldHandleGenericException() {
        // Given
        Exception ex = new RuntimeException("Generic error message");

        // When
        ResponseEntity<ErrorResponse> response = handler.handleGenericException(ex);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
        assertEquals("Internal Server Error", response.getBody().getError());
        assertEquals("Generic error message", response.getBody().getMessage());
    }
}

