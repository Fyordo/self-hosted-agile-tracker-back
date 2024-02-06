package com.rentinhand.rihtracker.dto.responses.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailResponse {
    private Long id;
    private String title;
    private String avatar;
    // TODO List<Task>
    // TODO List<User>
}
