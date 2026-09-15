package com.javarush.bogomolov.taskmanager.dto;

import com.javarush.bogomolov.taskmanager.repository.entity.JobStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class JobDto {
    private UUID jobId;
    private String jobName;
    private String jobInformation;
    private LocalDateTime deadline;
    private JobStatus jobStatus;
    public UUID getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobInformation() {
        return jobInformation;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }
}
