package com.mok.exceptions;

public class PokemonServiceException extends RuntimeException {
    public PokemonServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
