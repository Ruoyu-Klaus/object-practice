package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserService userService;

    @Override
    public List<Job> getAllJobs() {
        return null;
    }

    @Override
    public List<Job> getJobsByEmployerId(Integer employerId) {
        return null;
    }

    @Override
    public Job createJob(Job jobPosition) {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        jobPosition.setEmployerId(currentLoginUser.getId());
        return Job.fromEntity(jobRepository.save(jobPosition.toEntity()));
    }

    @Override
    public void deleteJobById(Integer jobId) {

    }
}
