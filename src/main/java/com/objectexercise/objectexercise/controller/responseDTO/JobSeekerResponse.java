package com.objectexercise.objectexercise.controller.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JobSeekerResponse {
    private Integer id;

    private String name;
}
