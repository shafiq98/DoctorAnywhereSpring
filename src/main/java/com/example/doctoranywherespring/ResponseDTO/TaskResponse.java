package com.example.doctoranywherespring.ResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class TaskResponse {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false, updatable=false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate createdDate;
    @Column(nullable=false)
    private Boolean complete;
    @Column
    private LocalDate completedDate;

}
