package com.youtube.jwt.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Employer")

public class Employer {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    private String employerName;
    private String employerFirstName;
    private String employerLastName;
    private String technology;

    public Employer() {
    }

    public Employer(String employerName, String employerFirstName, String employerLastName, String technology) {
        this.employerName = employerName;
        this.employerFirstName = employerFirstName;
        this.employerLastName = employerLastName;
        this.technology = technology;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerFirstName() {
        return employerFirstName;
    }

    public void setEmployerFirstName(String employerFirstName) {
        this.employerFirstName = employerFirstName;
    }

    public String getEmployerLastName() {
        return employerLastName;
    }

    public void setEmployerLastName(String employerLastName) {
        this.employerLastName = employerLastName;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
}

