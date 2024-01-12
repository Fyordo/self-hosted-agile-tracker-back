package com.rentinhand.rihtracker.dto.requests.project;

import lombok.Data;

import java.util.Set;

@Data
public abstract class ProjectDataRequest {
    private String title;
    private String avatar;
    private Long createdUserId;
    private Set<Long> userIds;
}
