package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    Job createJob(Job jobPosition);

}
