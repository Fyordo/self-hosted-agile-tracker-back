
package com.rentinhand.rihtracker.dto.task;

import com.rentinhand.rihtracker.dto.responses.task.TaskShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackedTaskDto {
    private LocalDateTime timeStart;
    private TaskShortResponse task;
}
