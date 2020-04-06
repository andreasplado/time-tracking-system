package com.logines.schedule.service;

import com.logines.schedule.model.Users;
import org.h2.engine.User;

import java.util.List;

public interface UserService {
    void save(Users user);

    Users findByUsername(String username);
    Users findByid(int id);
    List<Users> allUsers();
    boolean deleteUser(int id);
    boolean editUser(Users users);

}
