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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    private TaskService taskService;

    @MockBean
    TaskRepository taskRepository;

    @BeforeEach
    void init(@Mock TaskRepository taskRepository) {
        TaskResponse taskResponse = TaskResponse.builder()
                .id(1L)
                .title("Test Title")
                .description("Test Description")
                .complete(Boolean.FALSE)
                .createdDate(LocalDate.now())
                .completedDate(null)
                .build();
        taskService = new TaskService(taskRepository);
        Mockito.lenient().when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(taskResponse));
    }

    @Test
    void testGetTask() {
        TaskResponse taskResponse = TaskResponse.builder()
                .id(1L)
                .title("Test Title")
                .description("Test Description")
                .complete(Boolean.FALSE)
                .createdDate(LocalDate.now())
                .completedDate(null)
                .build();
        Assertions.assertEquals(taskResponse, taskService.getTask(1L));
    }

    @Test
    void testPostTask() {
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

    @Test
    void testPutTask() {
        TaskRequest taskRequest = TaskRequest.builder()
                .title("Test Title")
                .description("Test Description")
                .complete(Boolean.TRUE)
                .build();
        try {
            TaskResponse taskResponse = taskService.updateTask(1L, taskRequest);

            LocalDate dateNow = LocalDate.now();

            // Completed Date SHOULD NOT be null
            Assertions.assertNotNull(taskResponse.getCompletedDate());
            Assertions.assertEquals(taskResponse.getCompletedDate(), dateNow);

            // Complete Boolean Status SHOULD NOT be null
            Assertions.assertTrue(taskResponse.getComplete());

            // Assert that the ID we updated was correct
            Assertions.assertEquals(taskResponse.getId(), 1L);
            log.debug(String.valueOf(taskResponse));
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }


}