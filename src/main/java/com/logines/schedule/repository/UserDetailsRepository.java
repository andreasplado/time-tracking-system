package com.logines.schedule.repository;

import com.logines.schedule.model.UserDetails;
import com.logines.schedule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    @Query("SELECT s FROM UserDetails s WHERE s.username=:username FETCH NEXT 1 ROWS ONLY")
    UserDetails findByUsername(@Param("username") String name);
}
