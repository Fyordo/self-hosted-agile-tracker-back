package com.fyordo.shatback.dto.requests.timeEntry;

import com.fyordo.shatback.dto.requests.BaseRequest;
import lombok.Data;

@Data
public class TimeEntryCreateRequest extends BaseRequest {
    protected String description;

}
