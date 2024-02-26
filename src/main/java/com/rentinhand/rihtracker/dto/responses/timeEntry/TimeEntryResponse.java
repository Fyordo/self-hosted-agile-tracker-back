package com.rentinhand.rihtracker.dto.responses.timeEntry;

import com.rentinhand.rihtracker.dto.responses.task.TaskShortResponse;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeEntryResponse extends TimeEntryShortResponse{
    private TaskShortResponse task;
    private UserResponse user;
}
