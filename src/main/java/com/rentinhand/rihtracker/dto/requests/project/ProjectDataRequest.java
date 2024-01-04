package com.rentinhand.rihtracker.dto.requests.project;

import lombok.Data;

@Data
public abstract class ProjectDataRequest {
    private String title;
    private String avatar;
}
