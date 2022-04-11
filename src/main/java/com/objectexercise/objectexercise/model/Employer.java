package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.responseDTO.EmployerResponse;
import com.objectexercise.objectexercise.controller.responseDTO.JobSeekerResponse;
import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employer {
    private Integer id;

    private String name;

    private Integer userId;

    public static Employer fromEntity(EmployerEntity employerEntity) {
        return Employer.builder().id(employerEntity.getId()).userId(employerEntity.getUserId()).name(employerEntity.getName()).build();
    }

    public EmployerResponse toDTO(){
        return EmployerResponse.builder().id(id).name(name).build();
    }

}
