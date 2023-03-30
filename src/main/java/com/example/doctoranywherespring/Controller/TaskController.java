package com.example.doctoranywherespring.Controller;

import com.example.doctoranywherespring.RequestDTO.TaskRequest;
import com.example.doctoranywherespring.ResponseDTO.TaskResponse;
import com.example.doctoranywherespring.Service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> CreateTask(@RequestBody TaskRequest taskRequest) {
        try {
            log.debug("Creating a new task...");
            TaskResponse taskResponse = taskService.generateTask(taskRequest);
            log.debug("Successfully created new task...");
            return ResponseEntity.ok(taskResponse);
        } catch (Exception e) {
            log.error(e.getClass().getSimpleName());
            return ResponseEntity.badRequest().build();
        }
    }


}
