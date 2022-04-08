package com.objectexercise.objectexercise.controller;

import com.objectexercise.objectexercise.controller.requestDTO.UserCreationForm;
import com.objectexercise.objectexercise.controller.responseDTO.UserResponse;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<UserResponse> getUsers() {
        return userService.findAllUsers()
                .stream()
                .map(appUser -> UserResponse.builder().id(appUser.getId()).username(appUser.getName()).roles(appUser.getRoles()).build())
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public UserResponse createUser(@RequestBody UserCreationForm userRequest) {
        AppUser user = userService.createUser(AppUser.fromDTO(userRequest));
        return UserResponse.builder().id(user.getId()).accountName(user.getAccountName()).username(user.getName()).roles(user.getRoles()).build();
    }
}
