package com.wade.spring.boot.jpa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wade.spring.boot.jpa.crud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}