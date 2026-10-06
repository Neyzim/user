package com.neyzimho.user.infrastructure.exception;

public class CepFormatException extends RuntimeException {
    public CepFormatException(String message) {
        super(message);
    }

    public CepFormatException(String message, Throwable throwable){
        super(message, throwable);
    }
}
