package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;

import java.util.List;

public interface JobSeekerService {

    JobSeeker getJobSeekerByCurrentUser();

    JobSeeker getJobSeekerById(Integer jobSeekerId);

    Resume createResume(Resume resume);

    List<Resume> getJobSeekerResumes();

    List<Resume> getJobSeekerResumes(Integer jobSeekerId);

}
