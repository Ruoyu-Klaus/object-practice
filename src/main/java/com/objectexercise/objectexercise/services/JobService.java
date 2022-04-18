package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.Job;

import java.util.List;

public interface JobService {

    Job getJobById(Integer jobId);

    List<Job> getAllJobs();

    Job createJob(Job jobPosition);

    Job saveJobToUser(int jobId);
    
    List<Job> getSavedJobs();
}
