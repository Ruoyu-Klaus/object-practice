package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.model.JobApplication;

import java.util.List;

public interface JobApplicationService {
    JobApplication createJobApplication(JobApplicationForm jobApplicationForm);

    List<JobApplication> getJobApplications(Integer jobId);
}
