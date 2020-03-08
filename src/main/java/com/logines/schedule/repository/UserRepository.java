package com.logines.schedule.repository;

import com.logines.schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT s FROM users s WHERE s.name=:name")
    User findByUsername(@Param("name") String name);
}
