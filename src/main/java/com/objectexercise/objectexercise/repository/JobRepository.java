package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity,Integer> {

}
