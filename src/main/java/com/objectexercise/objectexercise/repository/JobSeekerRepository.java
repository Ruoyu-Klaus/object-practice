package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.JobSeekerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeekerEntity, Integer> {
    Optional<JobSeekerEntity> findByUserId(Integer userId);
}
