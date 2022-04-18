package com.objectexercise.objectexercise.services;

import com.objectexercise.objectexercise.controller.DTO.UserRole;
import com.objectexercise.objectexercise.exceptions.UserRuntimeException;
import com.objectexercise.objectexercise.model.AppUser;
import com.objectexercise.objectexercise.model.Employer;
import com.objectexercise.objectexercise.model.JobSeeker;
import com.objectexercise.objectexercise.repository.EmployerRepository;
import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import com.objectexercise.objectexercise.repository.Entity.JobSeekerEntity;
import com.objectexercise.objectexercise.repository.Entity.UserEntity;
import com.objectexercise.objectexercise.repository.JobSeekerRepository;
import com.objectexercise.objectexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRepository userRepository;
    final JobSeekerRepository jobSeekerRepository;
    final EmployerRepository employerRepository;
    final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByAccountName(accountName).orElseThrow(() ->
                new UsernameNotFoundException("User does not exist in the database"));
        AppUser appUser = AppUser.fromEntity(userEntity);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new User(appUser.getAccountName(), appUser.getPassword(), authorities);
    }


    @Override
    @Transactional
    public AppUser createUser(AppUser appUser) {
        if (appUser.getRoles().contains(UserRole.ADMIN)) {
            throw new UserRuntimeException("unsupported admin user creation");
        }
        if (userRepository.findByAccountName(appUser.getAccountName()).isPresent()) {
            throw new UserRuntimeException("account name already exist");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        UserEntity userEntity = userRepository.save(appUser.toEntity());
        if (appUser.getRoles().contains(UserRole.APPLICANT)) {
            jobSeekerRepository.save(JobSeekerEntity.builder().userId(userEntity.getId()).name(userEntity.getName()).build());
        }
        if (appUser.getRoles().contains(UserRole.RECRUITER)) {
            employerRepository.save(EmployerEntity.builder().userId(userEntity.getId()).name(userEntity.getName()).build());
        }
        return AppUser.fromEntity(userEntity);
    }

    @Override
    public AppUser getCurrentLoginUser() {
        String accountName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByAccountName(accountName)
                .orElseThrow(UserRuntimeException::UserNotFound);
        return AppUser.fromEntity(userEntity);
    }

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findAll().stream().map(AppUser::fromEntity).collect(Collectors.toList());
    }
    
    @Override
    public JobSeeker findJobSeekerByUserId(Integer userId) {
        JobSeekerEntity jobSeekerEntity = jobSeekerRepository.findByUserId(userId)
                .orElseThrow(UserRuntimeException::JobSeekerNotFound);
        return JobSeeker.fromEntity(jobSeekerEntity);
    }

    @Override
    public Employer findEmployerByUserId(Integer userId) {
        EmployerEntity employerEntity = employerRepository.findByUserId(userId)
                .orElseThrow(UserRuntimeException::EmployerNotFound);
        return Employer.fromEntity(employerEntity);
    }

}
