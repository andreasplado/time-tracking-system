package com.logines.schedule.repository;

import com.logines.schedule.model.UserDetails;
import com.logines.schedule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    @Query("SELECT s FROM user_details s WHERE s.username=:username")
    UserDetails findByUsername(@Param("username") String name);
}
