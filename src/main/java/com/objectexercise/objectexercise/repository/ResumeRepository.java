package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity,Integer> {
}
