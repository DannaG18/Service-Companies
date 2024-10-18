package com.sc.servicecompanies.infrastructure.utils.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {

    }

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
