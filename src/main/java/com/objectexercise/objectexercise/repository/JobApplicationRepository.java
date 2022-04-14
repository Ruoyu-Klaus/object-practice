package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.JobApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity,Integer> {
    List<JobApplicationEntity> findByJobId (Integer jobId);
}
