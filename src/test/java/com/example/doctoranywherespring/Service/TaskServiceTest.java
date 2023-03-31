package com.example.doctoranywherespring.Service;

import com.example.doctoranywherespring.Repository.TaskRepository;
import com.example.doctoranywherespring.RequestDTO.TaskRequest;
import com.example.doctoranywherespring.ResponseDTO.TaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    private TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService(taskRepository);
    }

    @Test
    void createNewTask() {
        TaskRequest taskRequest = TaskRequest.builder()
                .title("Test Title")
                .description("Test Description")
                .complete(Boolean.FALSE)
                .build();
        try {
            TaskResponse taskResponse = taskService.generateTask(taskRequest);

            LocalDate dateNow = LocalDate.now();

            Assertions.assertEquals(taskResponse.getCreatedDate(), dateNow);

            // Assert that Spring does not auto-generate any ID, as postgres will need to do that for us
            Assertions.assertNull(taskResponse.getId());
            // Assert that we are not generating a completed date as that should only be updated once the task is complete
            Assertions.assertNull(taskResponse.getCompletedDate());

            log.debug(String.valueOf(taskResponse));
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }
}