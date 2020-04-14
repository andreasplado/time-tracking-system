package com.logines.schedule.service;

import com.logines.schedule.model.Company;
import com.logines.schedule.model.Users;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> findAll();
    void save(Company company);
    Company findById(Integer id);
    List<Company> findByCompanyName(String companyName);
    boolean deleteCompany(Integer id);
}
