package com.arneriso.mybusinessapp.controllers.exeptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
