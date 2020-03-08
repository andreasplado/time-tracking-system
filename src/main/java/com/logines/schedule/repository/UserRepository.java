package com.logines.schedule.repository;

import com.logines.schedule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT s FROM Users s WHERE s.name=:name")
    Users findByUsername(@Param("name") String name);
}
