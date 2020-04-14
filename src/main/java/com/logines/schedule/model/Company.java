package com.logines.schedule.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "company",schema = "logines")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "company_name")
    private String companyNmae;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyNmae() {
        return companyNmae;
    }

    public void setCompanyNmae(String companyNmae) {
        this.companyNmae = companyNmae;
    }
}