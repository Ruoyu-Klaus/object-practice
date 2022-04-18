package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.exceptions.JobException;
import com.objectexercise.objectexercise.exceptions.UserRuntimeException;
import com.objectexercise.objectexercise.model.Employer;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.repository.EmployerRepository;
import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import com.objectexercise.objectexercise.repository.Entity.JobEntity;
import com.objectexercise.objectexercise.repository.Entity.JobSeekerSavedJobEntity;
import com.objectexercise.objectexercise.repository.JobRepository;
import com.objectexercise.objectexercise.repository.JobSeekerSavedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final UserService userService;
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final JobSeekerSavedRepository jobSeekerSavedRepository;

    @Override
    public Job getJobById(Integer jobId) {
        JobEntity jobEntity = jobRepository.findById(jobId).orElseThrow(JobException::JobNotFound);
        EmployerEntity employerEntity = employerRepository.findById(jobEntity.getEmployerId()).orElseThrow(UserRuntimeException::EmployerNotFound);
        Employer employer = Employer.fromEntity(employerEntity);
        return Job.fromEntity(jobEntity, employer);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobEntity -> Job.fromEntity(jobEntity, Employer.fromEntity(Objects.requireNonNull(employerRepository.findById(jobEntity.getEmployerId()).orElse(null)))))
                .collect(Collectors.toList());
    }

    @Override
    public Job createJob(Job jobPosition) {
        Employer employer = userService.findEmployerByUserId(userService.getCurrentLoginUser().getId());
        jobPosition.setEmployer(employer);
        return Job.fromEntity(jobRepository.save(jobPosition.toEntity()), employer);
    }

    @Override
    public Job saveJobToUser(int jobId) {
        JobSeeker jobSeeker = userService.findJobSeekerByUserId(userService.getCurrentLoginUser().getId());
        Job job = getJobById(jobId);
        JobSeekerSavedJobEntity jobSeekerSavedJobEntity = JobSeekerSavedJobEntity.builder().jobId(jobId).jobseekerId(jobSeeker.getId()).build();
        if (jobSeekerSavedRepository.findByJobIdAndJobseekerId(jobId, jobSeeker.getId()).isPresent()) {
            throw JobException.JobHasSaved();
        }
        jobSeekerSavedRepository.save(jobSeekerSavedJobEntity);
        return job;
    }

    @Override
    public List<Job> getSavedJobs() {
        JobSeeker jobSeeker = userService.findJobSeekerByUserId(userService.getCurrentLoginUser().getId());
        return jobRepository.findAllSavedJobsByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(jobEntity -> Job.fromEntity(jobEntity, Employer.fromEntity(Objects.requireNonNull(employerRepository.findById(jobEntity.getEmployerId()).orElse(null)))))
                .collect(Collectors.toList());
    }
}
