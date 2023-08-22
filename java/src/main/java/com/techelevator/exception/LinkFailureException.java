package com.techelevator.exception;

public class LinkFailureException extends DaoException {
    public LinkFailureException() {
        super();
    }

    public LinkFailureException(String message) {
        super(message);
    }

    public LinkFailureException(String message, Exception cause) {
        super(message, cause);
    }
}
