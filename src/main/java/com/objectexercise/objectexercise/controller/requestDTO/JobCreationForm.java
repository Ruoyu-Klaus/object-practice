package com.objectexercise.objectexercise.controller.requestDTO;

import com.objectexercise.objectexercise.controller.DTO.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobCreationForm {

    private String title;

    private JobType type;
}
