package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUser(AppUser appUser);

    List<AppUser> findAllUsers();
}
