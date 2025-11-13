package br.com.propagno.falecomjesus.adapter.input.rest.controller;

import br.com.propagno.falecomjesus.adapter.input.rest.dto.ExampleRequest;
import br.com.propagno.falecomjesus.adapter.input.rest.dto.ExampleResponse;
import br.com.propagno.falecomjesus.application.port.input.ExampleUseCase;
import br.com.propagno.falecomjesus.domain.entity.ExampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter de entrada REST - Example Controller
 */
@RestController
@RequestMapping("/api/v1/examples")
@RequiredArgsConstructor
public class ExampleController {
    
    private final ExampleUseCase exampleUseCase;
    
    @PostMapping
    public ResponseEntity<ExampleResponse> create(@RequestBody ExampleRequest request) {
        ExampleEntity entity = new ExampleEntity(request.getName(), request.getDescription());
        ExampleEntity created = exampleUseCase.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toResponse(created));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExampleResponse> findById(@PathVariable Long id) {
        return exampleUseCase.findById(id)
                .map(entity -> ResponseEntity.ok(toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<ExampleResponse>> findAll() {
        List<ExampleResponse> responses = exampleUseCase.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exampleUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    private ExampleResponse toResponse(ExampleEntity entity) {
        return ExampleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

