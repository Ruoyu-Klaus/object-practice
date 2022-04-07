package com.objectexercise.objectexercise.repository.Entity;


import com.objectexercise.objectexercise.controller.DTO.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="job")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String type;

    @Column(name = "employer_id")
    private Integer employerId;

    @Column(name = "post_date")
    @CreationTimestamp
    private Timestamp postDate;
}
