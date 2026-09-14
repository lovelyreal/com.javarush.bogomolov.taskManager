package com.javarush.bogomolov.taskmanager.repository.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(name = "jobStatus")
    private JobStatus jobStatus;

}
