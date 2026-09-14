package com.javarush.bogomolov.taskmanager.repository;

import com.javarush.bogomolov.taskmanager.repository.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends CrudRepository<Job, UUID> {
}
