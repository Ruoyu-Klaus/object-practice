package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.DTO.JobType;
import com.objectexercise.objectexercise.controller.requestDTO.JobCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.JobResponse;
import com.objectexercise.objectexercise.repository.Entity.JobEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private Integer id;

    private String title;

    private JobType type;

    private Employer employer;

    private Timestamp postDate;

    public static Job fromDTO(JobCreationForm JobRequest) {
        return Job.builder().title(JobRequest.getTitle()).type(JobRequest.getType()).build();
    }

    public JobResponse toJobDTO(){
        return JobResponse.builder()
                .id(id)
                .title(title)
                .type(type)
                .employer(employer.toDTO())
                .postDate(postDate)
                .build();
    }

    public static Job fromEntity(JobEntity jobEntity, Employer employer) {
        return new Job(jobEntity.getId(), jobEntity.getTitle(), JobType.valueOf(jobEntity.getType()), employer, jobEntity.getPostDate());
    }

    public JobEntity toEntity() {
        return JobEntity.builder().title(title).type(type.name()).employerId(employer.getId()).build();
    }
}
