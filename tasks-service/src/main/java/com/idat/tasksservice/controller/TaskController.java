package com.idat.tasksservice.controller;

import com.idat.tasksservice.client.NotificationClient;
import com.idat.tasksservice.model.Task;
import com.idat.tasksservice.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final NotificationClient notificationClient;

    public TaskController(TaskRepository taskRepository, NotificationClient notificationClient) {
        this.taskRepository = taskRepository;
        this.notificationClient = notificationClient;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getStatus() == null) task.setStatus("PENDIENTE");
        Task saved = taskRepository.save(task);
        try {
            notificationClient.sendNotification(
                    new NotificationClient.NotificationRequest("Se creó la tarea: " + saved.getTitle())
            );
        } catch (Exception e) {
            System.out.println("Notificación no enviada: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.findById(id).map(existing -> {
            existing.setTitle(task.getTitle());
            existing.setDescription(task.getDescription());
            existing.setStatus(task.getStatus());
            return ResponseEntity.ok(taskRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) return ResponseEntity.notFound().build();
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}