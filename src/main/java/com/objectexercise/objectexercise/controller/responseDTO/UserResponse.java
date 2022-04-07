package com.objectexercise.objectexercise.controller.responseDTO;

import com.objectexercise.objectexercise.controller.DTO.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserResponse {

    private Integer id;

    private String username;

    private String accountName;

    private Collection<UserRole> roles;

}
