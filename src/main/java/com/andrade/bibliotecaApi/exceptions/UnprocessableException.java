package com.andrade.bibliotecaApi.exceptions;

public class UnprocessableException extends RuntimeException {
    public UnprocessableException(String message) {
        super(message);
    }
}
