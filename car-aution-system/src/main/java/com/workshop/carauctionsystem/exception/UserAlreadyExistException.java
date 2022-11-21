package com.workshop.carauctionsystem.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
