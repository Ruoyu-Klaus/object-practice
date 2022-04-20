package com.objectexercise.objectexercise.controller;


import com.objectexercise.objectexercise.controller.requestDTO.ResumeCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.ResumeResponse;
import com.objectexercise.objectexercise.model.Resume;
import com.objectexercise.objectexercise.services.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final JobSeekerService jobSeekerService;

    @GetMapping("")
    public List<ResumeResponse> getUserResumes() {
        List<Resume> jobSeekerResumes = jobSeekerService.getJobSeekerResumes();
        return jobSeekerResumes.stream().map(Resume::toDTO).collect(Collectors.toList());
    }

    @PostMapping("")
    public ResumeResponse createResume(@RequestBody @Validated ResumeCreationForm resumeCreationForm) {
        Resume resume = jobSeekerService.createResume(Resume.fromDTO(resumeCreationForm));
        return resume.toDTO();
    }

}
