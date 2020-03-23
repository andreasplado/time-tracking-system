package com.logines.schedule.service;

import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;

import java.util.List;

public interface WorkHourService {
    List<WorkHour> findByUsername(String username);
}
