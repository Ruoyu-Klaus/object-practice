package com.objectexercise.objectexercise.repository.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="jobseeker")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer user_id;
}
