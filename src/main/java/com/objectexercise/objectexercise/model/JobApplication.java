package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplication {

    private Integer id;

    private Job job;

    private Resume resume;

    private ApplicationStatus status;

    private Timestamp applyDate;

}
