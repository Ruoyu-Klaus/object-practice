package com.objectexercise.objectexercise.controller.responseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CommonResponse<T> {
    private Integer status;

    private T data;

    private HttpStatus httpStatus;

    private String message;
}
