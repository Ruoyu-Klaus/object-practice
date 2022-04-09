package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.DTO.JobType;
import com.objectexercise.objectexercise.controller.responseDTO.JobSeekerResponse;
import com.objectexercise.objectexercise.repository.Entity.JobEntity;
import com.objectexercise.objectexercise.repository.Entity.JobSeekerEntity;
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

    public static JobSeeker fromEntity(JobSeekerEntity jobSeekerEntity) {
        return JobSeeker.builder().id(jobSeekerEntity.getId()).userId(jobSeekerEntity.getUserId()).name(jobSeekerEntity.getName()).build();
    }

    public JobSeekerResponse toDTO(){
        return JobSeekerResponse.builder().id(id).name(name).build();
    }

}
