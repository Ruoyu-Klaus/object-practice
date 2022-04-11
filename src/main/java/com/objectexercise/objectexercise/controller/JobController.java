package com.objectexercise.objectexercise.controller;

import com.objectexercise.objectexercise.controller.requestDTO.JobCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.JobResponse;
import com.objectexercise.objectexercise.model.Job;
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

    @GetMapping("")
    public List<JobResponse> getJobs() {
        return jobService.getAllJobs().stream().map(job -> JobResponse.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .type(job.getType())
                        .employer(job.getEmployer().toDTO())
                        .postDate(job.getPostDate())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public JobResponse addJob(@RequestBody @Validated JobCreationForm form) {
        Job job = jobService.createJob(Job.fromDTO(form));
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .type(job.getType())
                .employer(job.getEmployer().toDTO())
                .postDate(job.getPostDate())
                .build();
    }

}
