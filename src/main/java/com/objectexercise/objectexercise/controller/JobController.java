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
        return jobService.getAllJobs().stream().map(Job::toJobDTO).collect(Collectors.toList());
    }

    @PostMapping("")
    public JobResponse addJob(@RequestBody @Validated JobCreationForm form) {
        return jobService.createJob(Job.fromDTO(form)).toJobDTO();
    }

    @GetMapping("/{jobId}")
    public JobResponse getJobByJobId(@PathVariable String jobId) {
        return jobService.getJobById(Integer.parseInt(jobId)).toJobDTO();
    }

    @GetMapping("/bookmarks")
    public List<JobResponse> getSavedJobs() {
        return jobService.getSavedJobs().stream().map(Job::toJobDTO).collect(Collectors.toList());
    }

    @PostMapping("/bookmarks/{jobId}")
    public JobResponse saveJobToBookmarks(@PathVariable String jobId) {
        return jobService.saveJobToUser(Integer.parseInt(jobId)).toJobDTO();
    }
}
