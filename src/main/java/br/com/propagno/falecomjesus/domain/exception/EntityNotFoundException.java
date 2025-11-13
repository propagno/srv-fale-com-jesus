package br.com.propagno.falecomjesus.domain.exception;

/**
 * Exceção lançada quando uma entidade não é encontrada
 */
public class EntityNotFoundException extends DomainException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s com id %d não encontrado", entityName, id));
    }
}

