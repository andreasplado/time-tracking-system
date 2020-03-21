package com.logines.schedule.repository;

import com.logines.schedule.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserProfile, Integer> {
}
