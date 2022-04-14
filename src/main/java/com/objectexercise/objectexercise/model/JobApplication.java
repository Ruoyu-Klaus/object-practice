package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import com.objectexercise.objectexercise.repository.Entity.JobApplicationEntity;
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

    public static JobApplication fromEntity(JobApplicationEntity jobApplicationEntity) {
        return JobApplication
                .builder()
                .id(jobApplicationEntity.getId())
                .status(jobApplicationEntity.getStatus())
                .applyDate(jobApplicationEntity.getApplyDate())
                .build();
    }

    public static JobApplication fromEntity(JobApplicationEntity jobApplicationEntity, Job job, Resume resume) {
        return JobApplication
                .builder()
                .id(jobApplicationEntity.getId())
                .status(jobApplicationEntity.getStatus())
                .applyDate(jobApplicationEntity.getApplyDate())
                .job(job)
                .resume(resume)
                .build();
    }

}
