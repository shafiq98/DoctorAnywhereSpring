package com.example.doctoranywherespring.Service;

import com.example.doctoranywherespring.RequestDTO.TaskRequest;
import com.example.doctoranywherespring.ResponseDTO.TaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Slf4j
@Service
public class TaskService {
    public TaskService() {
    }

    public TaskResponse generateTask(TaskRequest taskRequest) {
        Long id = new Random().nextLong();
        LocalDate creationDate = LocalDate.now();

        return TaskResponse.builder()
                .id(id)
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .createdDate(creationDate)
                .build();
    }
}
