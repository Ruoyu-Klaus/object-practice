package com.objectexercise.objectexercise.controller;

import com.objectexercise.objectexercise.controller.requestDTO.JobRequest;
import com.objectexercise.objectexercise.controller.responseDTO.JobResponse;
import com.objectexercise.objectexercise.controller.responseDTO.UserResponse;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.services.JobService;
import com.objectexercise.objectexercise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final UserService userService;

    @GetMapping("")
    public List<JobResponse> getJobs() {
        return jobService.getAllJobs().stream().map(job -> JobResponse.builder()
                        .id(job.getId()).employer(getCurrenUserResponse()).postDate(job.getPostDate())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public JobResponse addJob(@RequestBody JobRequest form) {
        Job job = jobService.createJob(Job.fromDTO(form));
        return new JobResponse(job.getId(), job.getTitle(), job.getType(), getCurrenUserResponse(), job.getPostDate());
    }


    private UserResponse getCurrenUserResponse() {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        return UserResponse.builder().id(currentLoginUser.getId()).accountName(currentLoginUser.getAccountName()).username(currentLoginUser.getName()).roles(currentLoginUser.getRoles()).build();
    }
}