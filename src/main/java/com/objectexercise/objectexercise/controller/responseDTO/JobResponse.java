package com.objectexercise.objectexercise.controller.responseDTO;

import com.objectexercise.objectexercise.controller.DTO.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {

    private Integer id;

    private String title;

    private JobType type;

    private UserResponse employer;

    private Timestamp postDate;
}
