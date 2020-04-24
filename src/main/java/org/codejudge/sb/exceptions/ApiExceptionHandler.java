package org.codejudge.sb.exceptions;

import org.codejudge.sb.response.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<?> handle(org.hibernate.exception.ConstraintViolationException ex) {
        //you will get all javax failed validation, can be more than one
        //so you can return the set of error messages or just the first message
        String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
        Errors errors =  new Errors("failure",exceptionResponse);
        return new ResponseEntity<>(errors, null, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<?> handleApiException(ApiException ex)
    {
        Errors errors= new Errors("failure",ex.getMessage());
        return new ResponseEntity<>(errors, null, HttpStatus.BAD_REQUEST);
    }


}



