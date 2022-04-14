package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.model.JobApplication;

import java.util.List;

public interface JobApplicationService {
    JobApplication createJobApplication(JobApplicationForm jobApplicationForm);

    JobApplication updateApplicationStatus(Integer applicationId, ApplicationStatus applicationStatus);

    List<JobApplication> getJobApplications(Integer jobId);
}
