package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.exceptions.ResumeException;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.model.Resume;
import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import com.objectexercise.objectexercise.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    final JobSeekerService jobSeekerService;
    final ResumeRepository resumeRepository;

    @Override
    public Resume getResumeById(Integer resumeId) {
        ResumeEntity resumeEntity = resumeRepository.findById(resumeId).orElseThrow(ResumeException::ResumeNotFound);
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(resumeEntity.getJobSeekerId());
        return Resume.fromEntity(resumeEntity,jobSeeker);
    }
}
