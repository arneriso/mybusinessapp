package com.arneriso.mybusinessapp.controllers;

import com.arneriso.mybusinessapp.controllers.exeptions.InternalErrorException;
import com.arneriso.mybusinessapp.controllers.exeptions.InvalidInputException;
import com.arneriso.mybusinessapp.controllers.exeptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleInternalErrorException(InternalErrorException iex) {
        return new ResponseEntity<>(iex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException iix) {
        return new ResponseEntity<>(iix.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(NotFoundException nfe) {
        return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
    }
}
