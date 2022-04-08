package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserService userService;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll().stream().map(Job::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Job createJob(Job jobPosition) {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        jobPosition.setEmployerId(currentLoginUser.getId());
        return Job.fromEntity(jobRepository.save(jobPosition.toEntity()));
    }

}
