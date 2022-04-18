package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.exceptions.UserRuntimeException;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;
import com.objectexercise.objectexercise.repository.Entity.JobSeekerEntity;
import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import com.objectexercise.objectexercise.repository.JobSeekerRepository;
import com.objectexercise.objectexercise.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService {
    final JobSeekerRepository jobSeekerRepository;
    final ResumeRepository resumeRepository;
    final UserService userService;


    @Override
    public JobSeeker getJobSeekerByCurrentUser() {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        JobSeekerEntity jobSeeker = jobSeekerRepository.findByUserId(currentLoginUser.getId()).orElseThrow(UserRuntimeException::UserNotFound);
        return JobSeeker.fromEntity(jobSeeker);
    }

    @Override
    public JobSeeker getJobSeekerById(Integer jobSeekerId) {
        return JobSeeker.fromEntity(jobSeekerRepository.getById(jobSeekerId));
    }

    @Override
    public Resume createResume(Resume resume) {
        JobSeeker jobSeeker = getJobSeekerByCurrentUser();
        resume.setJobSeeker(jobSeeker);
        ResumeEntity resumeEntity = resumeRepository.save(resume.toEntity());
        return Resume.fromEntity(resumeEntity, jobSeeker);
    }

    @Override
    public List<Resume> getJobSeekerResumes() {
        JobSeeker jobSeeker = getJobSeekerByCurrentUser();
        return resumeRepository.findByJobSeekerId(jobSeeker.getId()).stream().map(resumeEntity -> Resume.fromEntity(resumeEntity, jobSeeker)).collect(Collectors.toList());
    }

    @Override
    public List<Resume> getJobSeekerResumes(Integer jobSeekerId) {
        JobSeekerEntity jobSeekerEntity = jobSeekerRepository.findById(jobSeekerId).orElseThrow(UserRuntimeException::JobSeekerNotFound);
        JobSeeker jobSeeker = JobSeeker.fromEntity(jobSeekerEntity);
        return resumeRepository.findByJobSeekerId(jobSeekerId).stream().map(resumeEntity -> Resume.fromEntity(resumeEntity, jobSeeker)).collect(Collectors.toList());
    }


}
