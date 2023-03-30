package com.example.doctoranywherespring.ResponseDTO;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskResponse {
    private Long id;

    @NonNull
    private String title;
    @NonNull
    private String description;
    private LocalDate createdDate;
    private LocalDate completedDate;
}
