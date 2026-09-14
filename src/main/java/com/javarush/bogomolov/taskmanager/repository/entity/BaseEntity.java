package com.javarush.bogomolov.taskmanager.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.security.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Id
    private UUID id;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp changedAt;



}
