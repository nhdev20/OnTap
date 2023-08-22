package com.techelevator.exception;

public class DataConflictException extends Exception {
    public DataConflictException() {
        super();
    }
    public DataConflictException(String message) {
        super(message);
    }
    public DataConflictException(String message, Exception cause) {
        super(message, cause);
    }
}
