package com.example.doctoranywherespring.RequestDTO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskRequest {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private Boolean complete;
}
