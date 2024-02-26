package com.rentinhand.rihtracker.dto;

import com.rentinhand.rihtracker.entities.*;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Timestamp deadline;
    private TaskType taskType;
    private Project project;
    private ScrumColumn scrumColumn;
    private User createdUser;
    private User maintainer;
    private List<TimeEntry> timeEntries = new ArrayList<>();
    private Set<User> users = new LinkedHashSet<>();
}
