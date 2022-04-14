package com.objectexercise.objectexercise.controller.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplicationForm {

    private Integer jobId;

    private Integer resumeId;
}
