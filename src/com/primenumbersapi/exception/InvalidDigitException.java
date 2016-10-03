package com.primenumbersapi.exception;

public class InvalidDigitException extends Exception {

    public InvalidDigitException() {
        super("One or more of the input digits are corrupted or invalid.");
    }
}
