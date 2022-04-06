package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.DTO.UserRole;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.repository.Entity.UserEntity;
import com.objectexercise.objectexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> first = userRepository.findByName(username).stream().findFirst();
        if (!first.isPresent()) {
            log.error("User does not exist in the database");
            throw new UsernameNotFoundException("User does not exist in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        AppUser appUser = AppUser.fromEntity(first.get());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new User(appUser.getName(), appUser.getPassword(), authorities);
    }


    @Override
    public AppUser createUser(AppUser appUser) {
        if(appUser.getRoles().contains(UserRole.ADMIN)){
            throw new RuntimeException("unsupported admin user creation");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        UserEntity userEntity = userRepository.save(appUser.toEntity());
        return AppUser.fromEntity(userEntity);
    }

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findAll().stream().map(AppUser::fromEntity).collect(Collectors.toList());
    }

}
