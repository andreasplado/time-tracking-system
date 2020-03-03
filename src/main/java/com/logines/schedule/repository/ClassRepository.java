package com.logines.schedule.repository;

import com.logines.schedule.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Class set name = :name, description = :description, teacher_name = :teacherName, time_minutes = :timeMinutes where id = :id")
    int updateClass(@Param("name") String name, @Param("description") String description, @Param("teacher_name") String teacherName,
                      @Param("timeMinutes") String timeMinutes, @Param("id") int id);
}
