package com.javarush.bogomolov.taskmanager.controller;


import com.javarush.bogomolov.taskmanager.repository.JobRepository;
import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import com.javarush.bogomolov.taskmanager.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JobStatusController {

    private JobService jobService;

    @PostMapping("/job/create")
    public UUID createJob(
            @RequestBody Job job
    ) {
        return jobService.createJob(job).getId();
    }

    @GetMapping
    public Job getJob(
           @RequestParam("UUID") UUID jobId
    ) {
        return jobService.getJobById(jobId);
    }


}
