package com.example.doctoranywherespring.Controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TaskControllerTest {

    @Autowired
    private TaskController taskController;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(taskController);
    }

}