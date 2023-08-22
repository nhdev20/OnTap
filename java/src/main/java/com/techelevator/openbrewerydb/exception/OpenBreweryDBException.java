package com.techelevator.openbrewerydb.exception;

public class OpenBreweryDBException extends RuntimeException {

    public OpenBreweryDBException() {
        super();
    }
    public OpenBreweryDBException(String message) {
        super(message);
    }
    public OpenBreweryDBException(String message, Exception cause) {
        super(message, cause);
    }
}
