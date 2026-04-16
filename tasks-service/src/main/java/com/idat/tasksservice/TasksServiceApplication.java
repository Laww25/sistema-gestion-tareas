package com.idat.tasksservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TasksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksServiceApplication.class, args);
    }

}
