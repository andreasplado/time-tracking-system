package com.logines.schedule.service;

import com.logines.schedule.model.Users;
import org.h2.engine.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    void save(Users user);

    Users findByUsername(String username);
    Users findByid(int id);
    List<Users> allUsersExceptiMine(String username);
    boolean deleteUser(int id);
    boolean editUser(Users users);

}
