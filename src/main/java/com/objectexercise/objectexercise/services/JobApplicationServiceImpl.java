package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import com.objectexercise.objectexercise.controller.DTO.JobType;
import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.exceptions.appUser.JobApplicationRuntimeException;
import com.objectexercise.objectexercise.model.Job;
import com.objectexercise.objectexercise.model.JobApplication;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;
import com.objectexercise.objectexercise.repository.Entity.JobApplicationEntity;
import com.objectexercise.objectexercise.repository.JobApplicationRepository;
import com.objectexercise.objectexercise.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    final JobService jobService;
    final JobSeekerService jobSeekerService;
    final JobApplicationRepository jobApplicationRepository;
    final ResumeRepository resumeRepository;

    @Override
    public JobApplication createJobApplication(JobApplicationForm jobApplicationForm) {
        Integer jobId = jobApplicationForm.getJobId();
        Job job = jobService.getJobById(jobId);
        Integer jobSeekerId = jobApplicationForm.getJobSeekerId();
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerByCurrentUser();
        if (!jobSeeker.getId().equals(jobSeekerId)) {
            throw new JobApplicationRuntimeException("you can only submit your own job application with your own id");
        }
        boolean isRequireResume = job.getType().equals(JobType.JREA);
        Optional<Resume> resumeOptional = jobSeekerService.getJobSeekerResumes(jobSeekerId).stream().filter(resume -> resume.getId().equals(jobApplicationForm.getResumeId())).findFirst();
        Resume resume = resumeOptional.orElse(null);
        if (isRequireResume && resume == null) {
            throw new JobApplicationRuntimeException("resume is required or does not exist");
        }
        Integer resumeId = resume != null ? resume.getId() : null;
        JobApplicationEntity jobApplicationEntityToSave = JobApplicationEntity.builder().jobId(jobId).employerId(job.getEmployer().getId()).status(ApplicationStatus.SUBMITTED.name()).jobseekerId(jobSeekerId).resumeId(resumeId).build();
        JobApplicationEntity jobApplicationEntity = jobApplicationRepository.save(jobApplicationEntityToSave);
        return JobApplication.builder()
                .id(jobApplicationEntity.getId())
                .jobSeeker(jobSeeker)
                .job(job)
                .resume(resume)
                .status(ApplicationStatus.valueOf(jobApplicationEntity.getStatus()))
                .applyDate(jobApplicationEntity.getApplyDate())
                .build();
    }
}
