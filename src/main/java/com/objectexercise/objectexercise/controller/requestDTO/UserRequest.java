package com.objectexercise.objectexercise.controller.requestDTO;


import com.objectexercise.objectexercise.controller.DTO.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserRequest {

    @NotNull(message = "user name must not be null")
    @Size(min = 4, max = 64, message = "user name length is out of range")
    private String username;

    @NotNull(message = "user password must not be null")
    @Size(min = 4, max = 64, message = "user name length is out of range")
    private String password;

    @NotNull(message = "user role must not be null")
    private Collection<UserRole> roles;

}
