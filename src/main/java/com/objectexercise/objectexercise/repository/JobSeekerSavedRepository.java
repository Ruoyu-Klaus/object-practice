package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.JobSeekerSavedJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerSavedRepository extends JpaRepository<JobSeekerSavedJobEntity,Integer> {

    Optional<JobSeekerSavedJobEntity> findByJobIdAndJobseekerId(Integer jobId,Integer JobseekerId);
}
