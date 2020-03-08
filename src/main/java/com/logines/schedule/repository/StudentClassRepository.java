package com.logines.schedule.repository;

import com.logines.schedule.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {
}
