package com.fyordo.shatback.dto.responses.timeEntry;

import com.fyordo.shatback.dto.responses.task.TaskShortResponse;
import com.fyordo.shatback.dto.responses.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeEntryResponse extends TimeEntryShortResponse{
    private TaskShortResponse task;
    private UserResponse user;
}
