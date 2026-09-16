package com.javarush.bogomolov.taskmanager.service;


import com.javarush.bogomolov.taskmanager.exception.EntityNotFoundException;
import com.javarush.bogomolov.taskmanager.repository.UserRepository;
import com.javarush.bogomolov.taskmanager.repository.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        user.setCreatedAt(LocalDateTime.now());
        user.setChangedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUser(UUID userId) {
        return userRepository.findUserById(userId);
    }

    @Transactional
    public User updateUser(UUID userId, User updateUser) {

        User userById = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userById.setEmail(updateUser.getEmail() != null ? updateUser.getEmail() : userById.getEmail());
        userById.setJobs(updateUser.getJobs() != null ? updateUser.getJobs() : userById.getJobs());
        userById.setLogin(updateUser.getLogin() != null ? updateUser.getLogin() : userById.getLogin());
        userById.setPassword(updateUser.getPassword() != null ? updateUser.getPassword() : userById.getPassword());
        userById.setChangedAt(LocalDateTime.now());
        return userRepository.save(userById);

    }

    @Transactional
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User with ID " + userId + " not found");
        }
        userRepository.deleteUserById(userId);
    }
}
