package com.example.doctoranywherespring.Service;

import com.example.doctoranywherespring.Repository.TaskRepository;
import com.example.doctoranywherespring.RequestDTO.TaskRequest;
import com.example.doctoranywherespring.ResponseDTO.TaskResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse generateTask(TaskRequest taskRequest) {
        Long id = new Random().nextLong();
        LocalDate creationDate = LocalDate.now();

        TaskResponse taskResponse = TaskResponse.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .createdDate(creationDate)
                .complete(taskRequest.getComplete())
                .build();
        taskRepository.save(taskResponse);
        return taskResponse;
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskResponse getTask(Long id) {
        Optional<TaskResponse> optionalTaskResponse = taskRepository.findById(id);
        if (optionalTaskResponse.isPresent()) {
            taskRepository.findById(id);
            return optionalTaskResponse.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Optional<TaskResponse> optionalTaskResponse = taskRepository.findById(id);
        if (optionalTaskResponse.isPresent()) {
            TaskResponse taskResponse = optionalTaskResponse.get();
            log.debug(String.valueOf(taskResponse));

            /* Logic to only update the title and description*/
            taskResponse.setTitle(taskRequest.getTitle());
            taskResponse.setDescription(taskRequest.getDescription());

            /* Logic to handle the completion status */
            taskResponse = updateCompletionStatus(taskRequest, taskResponse);
            taskRepository.save(taskResponse);

            return taskResponse;
        } else {
            throw new EntityNotFoundException();
        }
    }

    private TaskResponse updateCompletionStatus(TaskRequest taskRequest, TaskResponse taskResponse) {
        // Case 1: taskRequest is complete && taskResponse is complete
        TaskResponse result;
        if (taskRequest.getComplete().equals(taskResponse.getComplete())) {
            result = taskResponse;
        }
        // Case 2: taskRequest is true and existing Task is false
        else if (taskRequest.getComplete() == Boolean.TRUE) {
            taskResponse.setComplete(Boolean.TRUE);
            taskResponse.setCompletedDate(LocalDate.now());
            result = taskResponse;
        }
        // Case 3: taskRequest is false and existing Task is true
        else {
            taskResponse.setComplete(Boolean.FALSE);
            taskResponse.setCompletedDate(null);
            result = taskResponse;
        }
        return result;
    }

    public TaskResponse removeTask(Long id) {
        Optional<TaskResponse> optionalTaskResponse = taskRepository.findById(id);
        if (optionalTaskResponse.isPresent()) {
            taskRepository.deleteById(id);
            return optionalTaskResponse.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
