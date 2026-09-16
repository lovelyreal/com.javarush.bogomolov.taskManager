package com.javarush.bogomolov.taskmanager.service;

import com.javarush.bogomolov.taskmanager.dto.JobDto;
import com.javarush.bogomolov.taskmanager.repository.JobRepository;
import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
        job.setCreatedAt(LocalDateTime.now());
        job.setChangedAt(LocalDateTime.now());
        jobRepository.save(job);
        return job;
    }

    public Job getJobById(UUID id){

        return jobRepository.findById(id).isPresent() ? jobRepository.findById(id).get() : null;

    }

    public List<Job> getAllJobs(UUID userId, boolean showDeadJobs){
        return jobRepository.findJobsByOwnerIdAndDeadlineStatus(userId, showDeadJobs, LocalDateTime.now());
    }


    public Job editJob(JobDto jobDto){
        Job jobToEdit = jobRepository.findJobById(jobDto.getJobId());

        jobToEdit.setDeadline(jobDto.getDeadline() != null ? jobDto.getDeadline() : jobToEdit.getDeadline());
        jobToEdit.setJobInformation(jobDto.getJobInformation() != null ? jobDto.getJobInformation() : jobToEdit.getJobInformation());
        jobToEdit.setJobStatus(jobDto.getJobStatus() != null ? jobDto.getJobStatus() : jobToEdit.getJobStatus());
        jobToEdit.setName(jobDto.getJobName() != null ? jobDto.getJobName() : jobToEdit.getName());

        return jobRepository.save(jobToEdit);
    }

        public ResponseEntity<Void> deleteJob(UUID jobId){
            jobRepository.deleteById(jobId);
            return ResponseEntity.ok().build();
        }

}
