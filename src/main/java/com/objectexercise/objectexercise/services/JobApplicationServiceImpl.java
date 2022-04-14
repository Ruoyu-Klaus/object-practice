package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import com.objectexercise.objectexercise.controller.DTO.JobType;
import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.exceptions.appUser.JobApplicationRuntimeException;
import com.objectexercise.objectexercise.exceptions.appUser.UserRuntimeException;
import com.objectexercise.objectexercise.model.*;
import com.objectexercise.objectexercise.repository.Entity.JobApplicationEntity;
import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import com.objectexercise.objectexercise.repository.JobApplicationRepository;
import com.objectexercise.objectexercise.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private final UserService userService;
    private final JobService jobService;
    private final JobSeekerService jobSeekerService;
    private final JobApplicationRepository jobApplicationRepository;
    private final ResumeRepository resumeRepository;

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
        JobApplicationEntity jobApplicationEntityToSave = JobApplicationEntity.builder().jobId(jobId).employerId(job.getEmployer().getId()).jobseekerId(jobSeekerId).resumeId(resumeId).build();
        JobApplicationEntity jobApplicationEntity = jobApplicationRepository.save(jobApplicationEntityToSave);
        return JobApplication.builder()
                .id(jobApplicationEntity.getId())
                .job(job)
                .resume(resume)
                .status(jobApplicationEntity.getStatus())
                .applyDate(jobApplicationEntity.getApplyDate())
                .build();
    }

    @Override
    public JobApplication updateApplicationStatus(Integer applicationId, ApplicationStatus applicationStatus) {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        Employer employer = userService.findEmployerByUserId(currentLoginUser.getId());
        JobApplicationEntity jobApplicationEntity = jobApplicationRepository.findById(applicationId).orElseThrow(() -> new JobApplicationRuntimeException("applicaiton not found"));
        if (!employer.getId().equals(jobApplicationEntity.getEmployerId())) {
            throw new UserRuntimeException("employer: have no authorization for this request");
        }
        Job job = jobService.getJobById(jobApplicationEntity.getJobId());
        Resume resume = getResumeById(jobApplicationEntity.getResumeId());
        jobApplicationEntity.updateStatus(applicationStatus);
        JobApplicationEntity applicationEntity = jobApplicationRepository.save(jobApplicationEntity);
        return JobApplication
                .builder()
                .id(applicationEntity.getId())
                .job(job)
                .resume(resume)
                .status(applicationEntity.getStatus())
                .applyDate(applicationEntity.getApplyDate())
                .build();
    }

    @Override
    public List<JobApplication> getJobApplications(Integer jobId) {
        Job job = jobService.getJobById(jobId);
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        Employer employer = userService.findEmployerByUserId(currentLoginUser.getId());
        if (!Objects.equals(job.getEmployer().getId(), employer.getId())) {
            throw new UserRuntimeException("employer: [" + employer.getName() + "] have no authorization for this request");
        }
        List<JobApplicationEntity> jobApplications = jobApplicationRepository.findByJobId(jobId);

        return jobApplications.stream().map(applicationEntity -> JobApplication
                        .builder()
                        .id(applicationEntity.getId())
                        .job(job)
                        .resume(getResumeById(applicationEntity.getResumeId()))
                        .status(applicationEntity.getStatus())
                        .applyDate(applicationEntity.getApplyDate())
                        .build())
                .collect(Collectors.toList());
    }

    private Resume getResumeById(Integer resumeId) {
        ResumeEntity resumeEntity = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("resume does not exist"));
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(resumeEntity.getJobSeekerId());
        return Resume.fromEntity(resumeEntity, jobSeeker);
    }
}
