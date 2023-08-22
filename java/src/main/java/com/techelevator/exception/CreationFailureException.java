package com.techelevator.exception;

public class CreationFailureException extends DaoException {

    public CreationFailureException() {
        super();
    }

    public CreationFailureException(String message) {
        super(message);
    }

    public CreationFailureException(String message, Exception cause) {
        super(message, cause);
    }
}
