package com.rentinhand.rihtracker.dto.project;

import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import lombok.Data;

@Data
public class ProjectShortDto {
    private String title;

    public ProjectShortDto(Project project) {
        this.title = project.getTitle();
    }
}
