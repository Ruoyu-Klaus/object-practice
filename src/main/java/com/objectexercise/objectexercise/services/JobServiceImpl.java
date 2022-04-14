package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.exceptions.JobApplicationRuntimeException;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.Employer;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.repository.EmployerRepository;
import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import com.objectexercise.objectexercise.repository.Entity.JobEntity;
import com.objectexercise.objectexercise.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final UserService userService;

    @Override
    public Job getJobById(Integer jobId) {
        Optional<JobEntity> jobEntityOptional = jobRepository.findById(jobId);
        if (!jobEntityOptional.isPresent()) {
            throw new JobApplicationRuntimeException("Job not found with id: " + jobId);
        }
        JobEntity jobEntity = jobEntityOptional.get();
        EmployerEntity employerEntity = employerRepository.findById(jobEntity.getEmployerId()).orElseThrow(() -> new RuntimeException("employer does not exist"));
        Employer employer = Employer.fromEntity(employerEntity);
        return Job.fromEntity(jobEntityOptional.get(), employer);
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
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        Employer employer = userService.findEmployerByUserId(currentLoginUser.getId());
        jobPosition.setEmployer(employer);
        return Job.fromEntity(jobRepository.save(jobPosition.toEntity()), employer);
    }

}
