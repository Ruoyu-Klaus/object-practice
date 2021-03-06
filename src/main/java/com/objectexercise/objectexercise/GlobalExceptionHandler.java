package com.objectexercise.objectexercise;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.objectexercise.objectexercise.controller.responseDTO.ApiErrorResponse;
import com.objectexercise.objectexercise.exceptions.JobApplicationRuntimeException;
import com.objectexercise.objectexercise.exceptions.JobException;
import com.objectexercise.objectexercise.exceptions.ResumeException;
import com.objectexercise.objectexercise.exceptions.UserRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handle(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorResponse>> handle(MethodArgumentNotValidException ex) {
        List<ApiErrorResponse> errorResponses = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ApiErrorResponse(BAD_REQUEST, error.getDefaultMessage())
                )
                .collect(Collectors.toList());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponses);
    }

    @ExceptionHandler({
            InvalidFormatException.class,
            UserRuntimeException.class,
            JobApplicationRuntimeException.class,
            JobException.class,
            ResumeException.class})
    public ResponseEntity<ApiErrorResponse> handle(RuntimeException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

}