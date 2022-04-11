package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity,Integer> {
    List<ResumeEntity> findByJobSeekerId(Integer jobSeekerId);
}
