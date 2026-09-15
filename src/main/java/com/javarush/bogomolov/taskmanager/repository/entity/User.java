package com.javarush.bogomolov.taskmanager.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{


    @Column(name = "userLogin", nullable = false, unique = true)
    private String login;

    @Column(name = "userEmail", nullable = false, unique = true)
    private String email;

    @Column(name = "userPass", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false)
    private Role userRole;

    @OneToMany(mappedBy = "jobOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobs;

}
