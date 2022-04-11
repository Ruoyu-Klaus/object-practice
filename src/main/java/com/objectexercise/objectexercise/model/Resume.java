package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.requestDTO.ResumeCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.ResumeResponse;
import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

    private Integer id;

    private String name;

    private JobSeeker jobSeeker;

    public static Resume fromDTO(ResumeCreationForm resumeCreationForm){
        return Resume.builder().name(resumeCreationForm.getName()).build();
    }

    public ResumeResponse toDTO(){
        return ResumeResponse.builder().id(id).name(name).jobSeeker(jobSeeker.toDTO()).build();
    }

    public static Resume fromEntity(ResumeEntity resumeEntity,JobSeeker jobSeeker){
        return Resume.builder().id(resumeEntity.getId()).name(resumeEntity.getName()).jobSeeker(jobSeeker).build();
    }

    public ResumeEntity toEntity(){
        return ResumeEntity.builder().jobSeekerId(jobSeeker.getId()).name(name).build();
    }


}
