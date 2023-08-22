package com.techelevator.exception;

public class DuplicateRatingException extends Exception {

    public DuplicateRatingException() {
        super();
    }
    public DuplicateRatingException(String message) {
        super(message);
    }
    public DuplicateRatingException(String message, Exception cause) {
        super(message, cause);
    }
}
