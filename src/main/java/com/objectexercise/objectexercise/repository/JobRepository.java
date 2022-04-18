package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT a.`id`, a.`title`, a.`type`, a.`employer_id`, a.`post_date` FROM job a JOIN jobseeker_saved_job jsj on a.id = jsj.job_id WHERE jobseeker_id = ?1")
    List<JobEntity> findAllSavedJobsByJobSeekerId(Integer jobseekerId);
}
