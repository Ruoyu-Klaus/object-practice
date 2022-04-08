package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.exceptions.appUser.UserRuntimeException;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;
import com.objectexercise.objectexercise.repository.Entity.JobSeekerEntity;
import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import com.objectexercise.objectexercise.repository.JobSeekerRepository;
import com.objectexercise.objectexercise.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService {
    final JobSeekerRepository jobSeekerRepository;
    final ResumeRepository resumeRepository;
    final UserService userService;


    @Override
    public JobSeeker getJobSeekerInfoById(Integer jobSeekerId) {
        Optional<JobSeekerEntity> jobSeekerOptional = jobSeekerRepository.findById(jobSeekerId);
        if(!jobSeekerOptional.isPresent()){
            throw new UserRuntimeException("Job seeker not found");
        }
        JobSeekerEntity jobSeeker = jobSeekerOptional.get();
        return JobSeeker.builder().id(jobSeeker.getId()).name(jobSeeker.getName()).userId(jobSeeker.getUserId()).build();
    }

    @Override
    public Resume createResume(Resume resume) {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        Optional<JobSeekerEntity> jobseeker = jobSeekerRepository.findByUserId(currentLoginUser.getId());
        if(!jobseeker.isPresent()){
            throw new UserRuntimeException("you are not job seeker");
        }
        resume.setJobSeekerId(jobseeker.get().getId());
        ResumeEntity resumeEntity = resumeRepository.save(resume.toEntity());
        return Resume.fromEntity(resumeEntity);
    }



}
