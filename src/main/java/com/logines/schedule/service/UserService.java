package com.logines.schedule.service;

import com.logines.schedule.model.Users;

public interface UserService {
    void save(Users user);

    Users findByUsername(String username);
}
