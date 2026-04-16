package com.idat.tasksservice.repository;

import com.idat.tasksservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}