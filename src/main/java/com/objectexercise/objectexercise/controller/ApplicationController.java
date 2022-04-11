package com.objectexercise.objectexercise.controller;


import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.model.JobApplication;
import com.objectexercise.objectexercise.services.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {
    final JobApplicationService applicationService;

    @GetMapping()
    public String getApplications() {
        return "test";

    }

    @PostMapping()
    public JobApplication applyJob(@RequestBody @Validated JobApplicationForm applicationForm) {
        return applicationService.createJobApplication(applicationForm);
    }
}
