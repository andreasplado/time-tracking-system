package com.logines.schedule.repository;

import com.logines.schedule.model.Company;
import com.logines.schedule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query(value = "SELECT s FROM logines.company s WHERE s.company_name=:companyName", nativeQuery = true)
    List<Company> findByCompanyName(@Param("companyName") String companyName);
}
