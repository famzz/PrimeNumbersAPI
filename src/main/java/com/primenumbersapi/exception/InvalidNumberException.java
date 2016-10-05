package com.primenumbersapi.exception;

public class InvalidNumberException extends Exception {

    public InvalidNumberException() {
        super("Value does not represent an object of this type.");
    }
}
