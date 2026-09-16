package com.javarush.bogomolov.taskmanager.service;

import com.javarush.bogomolov.taskmanager.dto.JobDto;
import com.javarush.bogomolov.taskmanager.repository.JobRepository;
import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import com.javarush.bogomolov.taskmanager.repository.entity.User;
import com.javarush.bogomolov.taskmanager.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public Job createJob(
            Job job,
            Authentication authentication
    ) {
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        job.setId(UUID.randomUUID());
        job.setCreatedAt(LocalDateTime.now());
        job.setChangedAt(LocalDateTime.now());

        job.setJobOwner(user);

        return jobRepository.save(job);
    }

    public Job getJobById(UUID id) {

        return jobRepository.findById(id).isPresent() ? jobRepository.findById(id).get() : null;

    }

    public List<Job> getAllJobs(UUID userId, boolean showDeadJobs) {
        return jobRepository.findJobsByOwnerIdAndDeadlineStatus(userId, showDeadJobs, LocalDateTime.now());
    }

    @Transactional
    public Job editJob(JobDto jobDto) {
        Job jobToEdit = jobRepository.findJobById(jobDto.getJobId());

        jobToEdit.setDeadline(jobDto.getDeadline() != null ? jobDto.getDeadline() : jobToEdit.getDeadline());
        jobToEdit.setJobInformation(jobDto.getJobInformation() != null ? jobDto.getJobInformation() : jobToEdit.getJobInformation());
        jobToEdit.setJobStatus(jobDto.getJobStatus() != null ? jobDto.getJobStatus() : jobToEdit.getJobStatus());
        jobToEdit.setName(jobDto.getJobName() != null ? jobDto.getJobName() : jobToEdit.getName());

        return jobRepository.save(jobToEdit);
    }

    @Transactional
    public ResponseEntity<Void> deleteJob(UUID jobId) {
        jobRepository.deleteById(jobId);
        return ResponseEntity.ok().build();
    }

}
