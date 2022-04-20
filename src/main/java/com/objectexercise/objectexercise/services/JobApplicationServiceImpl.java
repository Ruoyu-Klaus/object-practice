package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;
import com.objectexercise.objectexercise.controller.DTO.JobType;
import com.objectexercise.objectexercise.controller.requestDTO.JobApplicationForm;
import com.objectexercise.objectexercise.exceptions.JobApplicationRuntimeException;
import com.objectexercise.objectexercise.exceptions.ResumeException;
import com.objectexercise.objectexercise.exceptions.UserRuntimeException;
import com.objectexercise.objectexercise.model.*;
import com.objectexercise.objectexercise.repository.Entity.JobApplicationEntity;
import com.objectexercise.objectexercise.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ResumeService resumeService;
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    @Transactional
    public JobApplication createJobApplication(JobApplicationForm jobApplicationForm) {
        Integer jobId = jobApplicationForm.getJobId();
        Job job = jobService.getJobById(jobId);
        Integer jobSeekerId = jobSeekerService.getJobSeekerByCurrentUser().getId();

        boolean isRequireResume = job.getType().equals(JobType.JREA);
        Optional<Resume> resumeOptional = jobSeekerService.getJobSeekerResumes(jobSeekerId).stream().filter(resume -> resume.getId().equals(jobApplicationForm.getResumeId())).findFirst();
        Resume resume = resumeOptional.orElse(null);
        if (isRequireResume && resume == null) {
            throw ResumeException.ResumeIsRequired();
        }
        Integer resumeId = resume != null ? resume.getId() : null;
        JobApplicationEntity jobApplicationEntityToSave = JobApplicationEntity.builder().jobId(jobId).employerId(job.getEmployer().getId()).jobseekerId(jobSeekerId).resumeId(resumeId).build();
        return JobApplication.fromEntity(jobApplicationRepository.save(jobApplicationEntityToSave), job, resume);
    }

    @Override
    @Transactional
    public JobApplication updateApplicationStatus(Integer applicationId, ApplicationStatus applicationStatus) {
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        Employer employer = userService.findEmployerByUserId(currentLoginUser.getId());
        JobApplicationEntity jobApplicationEntity = jobApplicationRepository.findById(applicationId).orElseThrow(JobApplicationRuntimeException::JobApplicationNotFound);
        if (!employer.getId().equals(jobApplicationEntity.getEmployerId())) {
            throw UserRuntimeException.AuthorizationError();
        }
        Job job = jobService.getJobById(jobApplicationEntity.getJobId());
        Resume resume = resumeService.getResumeById(jobApplicationEntity.getResumeId());
        jobApplicationEntity.updateStatus(applicationStatus);
        return JobApplication.fromEntity(jobApplicationRepository.save(jobApplicationEntity), job, resume);
    }

    @Override
    public List<JobApplication> getJobApplications(Integer jobId) {
        Job job = jobService.getJobById(jobId);
        AppUser currentLoginUser = userService.getCurrentLoginUser();
        Employer employer = userService.findEmployerByUserId(currentLoginUser.getId());
        if (!Objects.equals(job.getEmployer().getId(), employer.getId())) {
            throw UserRuntimeException.AuthorizationError();
        }
        List<JobApplicationEntity> jobApplications = jobApplicationRepository.findByJobId(jobId);

        return jobApplications.stream()
                .map(applicationEntity -> JobApplication.fromEntity(applicationEntity, job, resumeService.getResumeById(applicationEntity.getResumeId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobApplication> getUserJobApplications() {
        Integer jobSeekerId = jobSeekerService.getJobSeekerByCurrentUser().getId();
        List<JobApplicationEntity> jobApplications = jobApplicationRepository.findByJobseekerId(jobSeekerId);
        return jobApplications.stream()
                .map(applicationEntity -> JobApplication.fromEntity(applicationEntity, jobService.getJobById(applicationEntity.getJobId()), resumeService.getResumeById(applicationEntity.getResumeId())))
                .collect(Collectors.toList());
    }
}
