package com.arneriso.mybusinessapp.controllers.exeptions;

public class InternalErrorException extends Exception {
    public InternalErrorException(String message) {
        super(message);
    }
}
