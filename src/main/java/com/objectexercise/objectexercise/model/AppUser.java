package com.objectexercise.objectexercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.objectexercise.objectexercise.controller.DTO.UserRole;
import com.objectexercise.objectexercise.repository.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private Integer id;

    private String name;

    @JsonIgnore
    private String password;

    private Collection<UserRole> roles;


    public UserEntity toEntity(){
        String roles_to_save = roles.stream().map(Enum::name).collect(Collectors.joining(","));
        return UserEntity.builder().name(name).password(password).roles(roles_to_save).build();
    }

    public static AppUser fromEntity(UserEntity userEntity){
        Collection<UserRole> roles = Arrays.stream(userEntity.getRoles().split(",")).map(UserRole::valueOf).collect(Collectors.toList());
        return AppUser.builder().id(userEntity.getId()).name(userEntity.getName()).password(userEntity.getPassword()).roles(roles).build();
    }
}
