package br.com.propagno.falecomjesus.domain.exception;

/**
 * Exceção base do domínio
 */
public class DomainException extends RuntimeException {
    
    public DomainException(String message) {
        super(message);
    }
    
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

