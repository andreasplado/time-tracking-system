package com.logines.schedule.repository;

import com.logines.schedule.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Class set name = :name, description = :description, teacherName = :teacherName, timeMinutes = :timeMinutes where id = :id")
    int updateClass(@Param("name") String name, @Param("description") String description, @Param("teacherName") String teacherName,
                      @Param("timeMinutes") String timeMinutes, @Param("id") int id);
}
