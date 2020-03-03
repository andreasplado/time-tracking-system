package com.logines.schedule.repository;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
