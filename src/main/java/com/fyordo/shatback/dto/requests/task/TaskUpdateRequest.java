package com.fyordo.shatback.dto.requests.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskUpdateRequest extends TaskCreateRequest {

    private String description;

    private Timestamp deadline;

    private Long taskTypeId;

    private Long createdUserId;

    private Long maintainerUserId;
}
