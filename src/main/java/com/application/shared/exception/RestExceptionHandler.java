package com.application.shared.exception;

import com.application.rest.ApiError;
import com.application.shared.Constant;
import com.application.shared.exception.custom.BranchNotAvailableException;
import com.application.shared.exception.custom.EntityDuplicatedException;
import com.application.shared.exception.custom.EntityNotFoundException;
import com.application.shared.exception.resource.JsonExtractor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.I_AM_A_TEAPOT;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage(), JsonExtractor.extractJsonFromString(ex.getMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityDuplicatedException.class)
    protected ResponseEntity<Object> handleDuplicatedException(EntityDuplicatedException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), JsonExtractor.extractJsonFromString(ex.getMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(BranchNotAvailableException.class)
    protected ResponseEntity<Object> handleBranchNotAvailableException(BranchNotAvailableException ex){
        ApiError apiError = new ApiError(I_AM_A_TEAPOT, ex.getMessage(), JsonExtractor.extractJsonFromString(ex.getMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> validationErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors)
            validationErrors.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
        ApiError apiError = new ApiError(BAD_REQUEST, Constant.ARGUMENT_NOT_VALID_EXCEPTION, ex, validationErrors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
