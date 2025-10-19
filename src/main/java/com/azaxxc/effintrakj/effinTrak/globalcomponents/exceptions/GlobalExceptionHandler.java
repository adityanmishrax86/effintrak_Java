package com.azaxxc.effintrakj.effinTrak.globalcomponents.exceptions;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.exceptions.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorMessage body = new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis(), "An error occurred due to invalid argument");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.FORBIDDEN, System.currentTimeMillis(), "You do not have permission to access this resource");
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Validation Error", HttpStatus.BAD_REQUEST, System.currentTimeMillis(), ex.getBindingResult().toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException rsnfe, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(rsnfe.getMessage(), HttpStatus.NOT_FOUND, System.currentTimeMillis(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis(), "An unexpected error occurred");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }



}
