package com.rentinhand.rihtracker.dto.responses.task;

import com.rentinhand.rihtracker.dto.responses.timeEntry.TimeEntryResponse;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse extends TaskShortResponse {
    protected String description;
    protected UserResponse createdUser;
    private List<TimeEntryResponse> timeEntries;
    private Set<UserResponse> users;
}
