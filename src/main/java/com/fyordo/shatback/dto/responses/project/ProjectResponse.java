package com.fyordo.shatback.dto.responses.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentinhand.rihtracker.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    protected Long id;
    protected String title;
    protected String avatar;

    public ProjectResponse(Project project){
        this.id = project.getId();
        this.title = project.getTitle();
        this.avatar = project.getAvatar();
    }
}
