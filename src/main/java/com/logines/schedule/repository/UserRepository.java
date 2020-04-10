package com.logines.schedule.repository;

import com.logines.schedule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT s FROM Users s WHERE s.username=:username")
    Users findByUsername(@Param("username") String name);

    void save(User user);

    @Modifying
    @Query(value = "DELETE FROM logines.users WHERE logines.users.username=:username", nativeQuery = true)
    void deleteUser(@Param("username") String username);

    @Query("SELECT s FROM Users s WHERE s.fullname=:username")
    List<Users> findByFullname(@Param("username") String name);
}
