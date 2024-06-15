package com.fyordo.shatback.dto.responses.scrumColumn;

import com.fyordo.shatback.dto.responses.task.TaskResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrumColumnResponse extends ScrumColumnShortResponse {
    protected List<TaskResponse> tasks;
}
