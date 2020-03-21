package com.logines.schedule.repository;

import com.logines.schedule.model.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<WorkHour, Integer> {
}
