package com.techelevator.exception;

public class DuplicateReviewException extends Exception {

    public DuplicateReviewException() {
        super();
    }
    public DuplicateReviewException(String message) {
        super(message);
    }
    public DuplicateReviewException(String message, Exception cause) {
        super(message, cause);
    }
}
