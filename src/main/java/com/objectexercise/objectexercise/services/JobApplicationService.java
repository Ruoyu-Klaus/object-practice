package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.model.JobApplication;

public interface JobApplicationService {
    JobApplication createJobApplication(JobApplicationForm jobApplicationForm);
}
