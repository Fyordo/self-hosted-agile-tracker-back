package com.rentinhand.rihtracker.dto.requests.timeEntry;

import com.rentinhand.rihtracker.dto.requests.BaseRequest;
import lombok.Data;

@Data
public class TimeEntryCreateRequest extends BaseRequest {
    protected String description;

}
