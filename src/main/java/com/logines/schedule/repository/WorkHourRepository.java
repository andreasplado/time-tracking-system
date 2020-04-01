package com.logines.schedule.repository;

import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Integer> {

    @Query("SELECT s FROM WorkHour s WHERE s.username=:username")
    List<WorkHour> findWorkHoursByUsername(@Param("username") String name);

    @Modifying
    @Query(value = "DELETE FROM logines.work_hour WHERE to_date(cast(created_at as TEXT),'dd-MM-yyyy HH24:mi') < NOW() + INTERVAL '30 days'", nativeQuery = true)
    void deleteLast30DaysWorkHours();
}
