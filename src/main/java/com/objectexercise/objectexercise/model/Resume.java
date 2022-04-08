package com.objectexercise.objectexercise.model;

import com.objectexercise.objectexercise.controller.requestDTO.ResumeCreationForm;
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

    private Integer jobSeekerId;

    public static Resume fromDTO(ResumeCreationForm resumeCreationForm){
        return Resume.builder().name(resumeCreationForm.getName()).build();
    }

    public static Resume fromEntity(ResumeEntity resumeEntity){
        return Resume.builder().id(resumeEntity.getId()).name(resumeEntity.getName()).jobSeekerId(resumeEntity.getJobseekerId()).build();
    }

    public ResumeEntity toEntity(){
        return ResumeEntity.builder().jobseekerId(jobSeekerId).name(name).build();
    }


}
