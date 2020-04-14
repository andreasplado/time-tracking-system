package com.logines.schedule.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "company",schema = "logines")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null) created_at = new Timestamp(System.currentTimeMillis());
        if (this.updated_at == null) updated_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at =  new Timestamp(System.currentTimeMillis());
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getUpdated_at() {
        return this.updated_at;
    }
}