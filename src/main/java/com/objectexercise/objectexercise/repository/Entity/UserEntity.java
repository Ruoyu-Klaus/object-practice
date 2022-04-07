package com.objectexercise.objectexercise.repository.Entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "account_name")
    private String accountName;

    private String password;

    private String roles;
}
