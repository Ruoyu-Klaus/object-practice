package com.objectexercise.objectexercise.repository;

import com.objectexercise.objectexercise.repository.Entity.EmployerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<EmployerEntity,Integer> {
}
