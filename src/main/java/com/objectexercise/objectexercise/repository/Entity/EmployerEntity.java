package com.objectexercise.objectexercise.repository.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class EmployerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "user_id")
    private Integer userId;
}
