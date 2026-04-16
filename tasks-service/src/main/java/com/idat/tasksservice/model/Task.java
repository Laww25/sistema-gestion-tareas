package com.idat.tasksservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    private Long userId;
}