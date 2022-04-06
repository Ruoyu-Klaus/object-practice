package com.objectexercise.objectexercise;

import com.objectexercise.objectexercise.controller.responseDTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handle(MethodArgumentNotValidException ex){
        List<ErrorResponse> errorResponses = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ErrorResponse
                        .builder()
                        .message(error.getDefaultMessage())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);
    }


}