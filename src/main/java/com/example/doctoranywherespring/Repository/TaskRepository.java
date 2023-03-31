package com.example.doctoranywherespring.Repository;

import com.example.doctoranywherespring.ResponseDTO.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskResponse, Long> {
}
