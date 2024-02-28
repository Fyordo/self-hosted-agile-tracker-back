package com.rentinhand.rihtracker.builders;

import com.rentinhand.rihtracker.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskBuilder {
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
