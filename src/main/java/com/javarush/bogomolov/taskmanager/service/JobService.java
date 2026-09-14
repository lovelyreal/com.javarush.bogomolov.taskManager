package com.javarush.bogomolov.taskmanager.service;

import com.javarush.bogomolov.taskmanager.repository.JobRepository;
import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Transactional
    public Job createJob(Job job){
        job.setId(UUID.randomUUID());
        jobRepository.save(job);
        return job;
    }

    public Job getJobById(UUID id){

        return jobRepository.findById(id).isPresent() ? jobRepository.findById(id).get() : null;

    }


}
