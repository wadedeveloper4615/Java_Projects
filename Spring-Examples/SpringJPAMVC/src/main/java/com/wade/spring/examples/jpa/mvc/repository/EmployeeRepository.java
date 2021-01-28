package com.wade.spring.examples.jpa.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wade.spring.examples.jpa.mvc.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
