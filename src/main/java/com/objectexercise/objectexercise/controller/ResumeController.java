package com.objectexercise.objectexercise.controller;


import com.objectexercise.objectexercise.controller.requestDTO.ResumeCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.JobSeekerResponse;
import com.objectexercise.objectexercise.controller.responseDTO.ResumeResponse;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;
import com.objectexercise.objectexercise.services.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    final JobSeekerService jobSeekerService;

    @PostMapping("")
    public ResumeResponse createResume(@RequestBody @Validated ResumeCreationForm resumeCreationForm)
    {
        Resume resume = jobSeekerService.createResume(Resume.fromDTO(resumeCreationForm));
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerInfoById(resume.getJobSeekerId());
        return ResumeResponse.builder().id(resume.getId()).name(resume.getName()).jobSeeker(JobSeekerResponse.builder().id(jobSeeker.getId()).name(jobSeeker.getName()).build()).build();
    }

}
