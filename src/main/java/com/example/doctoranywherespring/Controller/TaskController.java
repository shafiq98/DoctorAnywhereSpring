package com.example.doctoranywherespring.Controller;

import com.example.doctoranywherespring.RequestDTO.TaskRequest;
import com.example.doctoranywherespring.ResponseDTO.TaskResponse;
import com.example.doctoranywherespring.Service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
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

    @GetMapping
    public ResponseEntity<List<TaskResponse>> GetAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> GetTaskByID(@PathVariable Long id) {
        log.debug(String.format("Requested Task ID = {%o}", id));
        try {
            TaskResponse taskResponse = taskService.getTask(id);
            return ResponseEntity.ok(taskResponse);
        } catch (EntityNotFoundException e) {
            log.debug(String.format("Task with ID : {%o} not found", id));
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> UpdateTask(@PathVariable Long id,
                                                   @RequestBody TaskRequest taskRequest) {
        log.debug(String.format("Requested Task ID = {%o}", id));
        try {
            TaskResponse taskResponse = taskService.updateTask(id, taskRequest);
            return ResponseEntity.ok(taskResponse);
        } catch (EntityNotFoundException e) {
            log.debug(String.format("Task with ID : {%o} not found", id));
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> DeleteTask(@PathVariable Long id) {
        log.debug(String.format("Requested Task ID = {%o}", id));
        try {
            TaskResponse taskResponse = taskService.removeTask(id);
            return ResponseEntity.ok(taskResponse);
        } catch (EntityNotFoundException e) {
            log.debug(String.format("Task with ID : {%o} not found", id));
            return ResponseEntity.notFound().build();
        }
    }

}
