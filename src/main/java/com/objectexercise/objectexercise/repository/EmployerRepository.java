package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<EmployerEntity,Integer> {
    Optional<EmployerEntity> findByUserId(Integer userId);
}
