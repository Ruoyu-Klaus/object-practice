package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    List<Job> getJobsByEmployerId(Integer employerId);

    Job createJob(Job jobPosition);

    void deleteJobById(Integer jobId);
}
