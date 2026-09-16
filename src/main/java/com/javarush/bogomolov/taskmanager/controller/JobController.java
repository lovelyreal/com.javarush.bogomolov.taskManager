package com.javarush.bogomolov.taskmanager.controller;

import com.javarush.bogomolov.taskmanager.dto.JobDto;
import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import com.javarush.bogomolov.taskmanager.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public UUID createJob(
            @RequestBody @Validated Job job
    ) {
        return jobService.createJob(job).getId();
    }

    @GetMapping("/find")
    public Job getJob(
            @RequestParam("UUID") UUID jobId
    ) {
        return jobService.getJobById(jobId);
    }

    @GetMapping("/get")
    public List<Job> getAllUserJobs(
            @RequestParam("UUID") UUID userId,
            @RequestParam("showDeadJobs") boolean showDeadJobs
    ) {
        return jobService.getAllJobs(userId, showDeadJobs);
    }

    @PostMapping("/edit")
    public Job editJobById(
            @RequestBody JobDto jobDto
    ) {
        return jobService.editJob(jobDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteJob
            (
                    @RequestParam UUID jobId
            ) {
        return jobService.deleteJob(jobId);
    }

}
