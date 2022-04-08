package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;

public interface JobSeekerService {

    JobSeeker getJobSeekerInfoById(Integer jobSeekerId);

    Resume createResume(Resume resume);

}
