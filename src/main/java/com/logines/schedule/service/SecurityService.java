package com.logines.schedule.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
