package com.officersofttest.core.excepetions;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler()
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler()
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> handleValidationError(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), errorMessage, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler()
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<StandardError> handleMethodNotAllowedError(Exception e) {
        StandardError err = new StandardError(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
    }

    @ExceptionHandler()
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<StandardError> handleInternalServerError(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> x = e.getConstraintViolations();
        String errorMessage = null;
        for (ConstraintViolation<?> constraintViolation : x) {
            errorMessage = constraintViolation.getMessage();
        }
        StandardError err = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage,
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}