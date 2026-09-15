package com.javarush.bogomolov.taskmanager.repository;

import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {


    Job findJobById(UUID id);

    @Query("SELECT j FROM Job j WHERE j.jobOwner.id = :jobOwnerId " +
            "AND (:showExpired = true OR j.deadline >= :now)")
    List<Job> findJobsByOwnerIdAndDeadlineStatus(
            @Param("jobOwnerId") UUID jobOwnerId,
            @Param("showExpired") boolean showExpired,
            @Param("now") LocalDateTime now
    );
}
