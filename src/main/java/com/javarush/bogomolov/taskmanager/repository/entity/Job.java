package com.javarush.bogomolov.taskmanager.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(name = "jobStatus")
    private JobStatus jobStatus;

    @ManyToOne()
    @JoinColumn(name = "jobOwnerId")
    private User jobOwner;

    @Column(name = "jobName")
    private String name;

    @Column(name = "jobInfo")
    private String jobInformation;

    @Column(name = "jobDeadline")
    private LocalDateTime deadline;

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public User getJobOwner() {
        return jobOwner;
    }

    public void setJobOwner(User jobOwner) {
        this.jobOwner = jobOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobInformation() {
        return jobInformation;
    }

    public void setJobInformation(String jobInformation) {
        this.jobInformation = jobInformation;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
