package com.objectexercise.objectexercise.repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "jobseeker_saved_job")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerSavedJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jobseeker_id")
    private Integer jobseekerId;

    @Column(name = "job_id")
    private Integer jobId;

}
