package com.javarush.bogomolov.taskmanager.repository;

import com.javarush.bogomolov.taskmanager.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserById(UUID id);
}
