package com.youtube.jwt.entity;

import javax.persistence.*;


@Entity
@Table(name="Job")
public class Job {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long jobId;
    private String jobTitle;
    private String domain;
    private String technology;
    private String applied;

    public Job() {
    }

    public Job(int id,String jobTitle, String technology, String applied) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.domain = domain;
        this.technology = technology;
        this.applied = applied;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setId(Long Id) {this.jobId = jobId;}

    public String getJobTitle() {return jobTitle;}

    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}

    public String getDomain() {return domain;}

    public void setDomain(String domain) {this.domain = domain;}

    public String getTechnology() {return technology;}

    public void setTechnology(String technology) {this.technology = technology;}

    public String getApplied() {return applied;}

    public void setApplied(String applied) {this.applied = applied;}

}
