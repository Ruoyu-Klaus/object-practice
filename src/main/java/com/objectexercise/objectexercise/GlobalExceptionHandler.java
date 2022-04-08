package com.objectexercise.objectexercise;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.objectexercise.objectexercise.controller.responseDTO.ErrorResponse;
import com.objectexercise.objectexercise.exceptions.appUser.UserRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handle(MethodArgumentNotValidException ex){
        List<ErrorResponse> errorResponses = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ErrorResponse
                        .builder()
                        .message(error.getDefaultMessage())
                        .error(BAD_REQUEST.getReasonPhrase())
                        .status(BAD_REQUEST.value())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponses);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handle(InvalidFormatException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .status(BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UserRuntimeException.class)
    public ResponseEntity<ErrorResponse> handle(UserRuntimeException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .status(BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

}