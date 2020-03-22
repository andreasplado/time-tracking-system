package com.logines.schedule.repository;

import com.logines.schedule.model.Student;
import com.logines.schedule.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    @Query("DELETE s FROM user_profile s WHERE s.username=:username")
    void deleteByFirstName(@Param("username") String username);
}
