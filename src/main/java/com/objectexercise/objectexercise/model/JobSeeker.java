package com.objectexercise.objectexercise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker {

    private Integer id;

    private String name;

    private Integer userId;

}
