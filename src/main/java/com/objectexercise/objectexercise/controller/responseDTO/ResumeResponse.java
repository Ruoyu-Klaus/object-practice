package com.objectexercise.objectexercise.controller.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponse {

    private Integer id;

    private String name;

    private Integer jobSeekerId;
}
