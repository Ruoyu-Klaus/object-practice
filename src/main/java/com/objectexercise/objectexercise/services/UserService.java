package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.Employer;

import java.util.List;

public interface UserService {
    AppUser createUser(AppUser appUser);

    AppUser getCurrentLoginUser();

    List<AppUser> findAllUsers();

    AppUser findUserById(Integer userId);

    Employer findEmployerByUserId(Integer userId);
}
