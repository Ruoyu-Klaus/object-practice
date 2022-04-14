package com.objectexercise.objectexercise.controller.requestDTO;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationStatusUpdate {
    private ApplicationStatus applicationStatus;
}
