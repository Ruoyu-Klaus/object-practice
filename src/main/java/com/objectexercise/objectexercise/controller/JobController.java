package com.objectexercise.objectexercise.controller;

import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationStatusUpdate;
import com.objectexercise.objectexercise.controller.requestDTO.JobCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.JobResponse;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.model.JobApplication;
import com.objectexercise.objectexercise.services.JobApplicationService;
import com.objectexercise.objectexercise.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    final JobApplicationService applicationService;

    @GetMapping("")
    public List<JobResponse> getJobs() {
        return jobService.getAllJobs().stream().map(Job::toJobDTO).collect(Collectors.toList());
    }

    @GetMapping("/{jobId}")
    public JobResponse getJobByJobId(@PathVariable String jobId) {
        return jobService.getJobById(Integer.parseInt(jobId)).toJobDTO();
    }

    @PostMapping("")
    public JobResponse addJob(@RequestBody @Validated JobCreationForm form) {
        return jobService.createJob(Job.fromDTO(form)).toJobDTO();
    }

    @GetMapping("/{jobId}/applications")
    public List<JobApplication> getJobApplications(@PathVariable String jobId) {
        return applicationService.getJobApplications(Integer.parseInt(jobId));
    }

    @PostMapping("/{jobId}/applications")
    public JobApplication applyJob(@PathVariable String jobId, @RequestBody @Validated JobApplicationForm applicationForm) {
        applicationForm.setJobId(Integer.parseInt(jobId));
        return applicationService.createJobApplication(applicationForm);
    }

    @PatchMapping("/{jobId}/applications/{applicationId}")
    public JobApplication updateStatus(@PathVariable String applicationId, @RequestBody JobApplicationStatusUpdate status) {
        return applicationService.updateApplicationStatus(Integer.parseInt(applicationId), status.getApplicationStatus());
    }
}
