package com.objectexercise.objectexercise.controller;

import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationStatusUpdate;
import com.objectexercise.objectexercise.model.JobApplication;
import com.objectexercise.objectexercise.services.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApplicationController {
    private final JobApplicationService applicationService;

    @GetMapping(value = "applications")
    public List<JobApplication> getUserJobApplications() {
        return applicationService.getUserJobApplications();
    }

    @GetMapping(value = "/jobs/{jobId}/applications")
    public List<JobApplication> getJobApplications(@PathVariable String jobId) {
        return applicationService.getJobApplications(Integer.parseInt(jobId));
    }

    @PostMapping("/jobs/{jobId}/applications")
    public JobApplication applyJob(@PathVariable String jobId, @RequestBody @Validated JobApplicationForm applicationForm) {
        applicationForm.setJobId(Integer.parseInt(jobId));
        return applicationService.createJobApplication(applicationForm);
    }

    @PatchMapping("/jobs/{jobId}/applications/{applicationId}")
    public JobApplication updateStatus(@PathVariable String applicationId, @RequestBody JobApplicationStatusUpdate status, @PathVariable String jobId) {
        return applicationService.updateApplicationStatus(Integer.parseInt(applicationId), status.getApplicationStatus());
    }
}
