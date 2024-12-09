package org.acme.infrastructure.driven.shared;

public class TokenStorageException extends RuntimeException{
    public TokenStorageException(String message) {
        super(message);
    }

    public TokenStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
