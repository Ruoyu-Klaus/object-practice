package com.objectexercise.objectexercise.controller.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplicationForm {

    @NotNull(message = "jobId must not be null")
    private Integer jobId;

    private Integer jobSeekerId;

    private Integer resumeId;
}
