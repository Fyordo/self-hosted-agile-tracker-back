package com.rentinhand.rihtracker.dto.responses.task;

import com.fyordo.shatback.dto.responses.task.TaskShortResponse;
import com.fyordo.shatback.dto.responses.timeEntry.TimeEntryResponse;
import com.fyordo.shatback.dto.responses.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
