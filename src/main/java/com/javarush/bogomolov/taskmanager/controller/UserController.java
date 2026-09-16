package com.javarush.bogomolov.taskmanager.controller;


import com.javarush.bogomolov.taskmanager.repository.entity.User;
import com.javarush.bogomolov.taskmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(
            @RequestBody User user
    ) {
        return userService.createUser(user);
    }

    @GetMapping("/get")
    public User getUserById(
            @RequestParam UUID userId
    ) {
        return userService.getUser(userId);
    }

    @PostMapping("/update")
    public User updateUser(
            @RequestParam("UserId") UUID userId,
            @RequestBody User updateUser
    ) {
        return userService.updateUser(userId, updateUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserById(@RequestParam("UserId") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been successfully deleted.");
    }

}
