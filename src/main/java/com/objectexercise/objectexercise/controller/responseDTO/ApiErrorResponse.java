package com.objectexercise.objectexercise.controller.responseDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private String message;

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp = new Date();

    public ApiErrorResponse(HttpStatus status) {
        this.status = status;
    }

    public ApiErrorResponse(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}