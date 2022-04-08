package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<EmployerEntity,Integer> {
}
