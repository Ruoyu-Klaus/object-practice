package com.objectexercise.objectexercise.repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "job_application")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "employer_id")
    private Integer employerId;

    @Column(name = "jobseeker_id")
    private Integer jobseekerId;

    @Column(name = "resume_id")
    private Integer resumeId;

    @Column(name = "status", columnDefinition = "VARCHAR(64) DEFAULT 'SUBMITTED'")
    private String status = "SUBMITTED";

    @CreationTimestamp
    @Column(name = "apply_date")
    private Timestamp applyDate;

}