package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.fyordo.shatback.dto.responses.ListResponse;
import com.fyordo.shatback.dto.responses.timeEntry.TimeEntryResponse;
import com.fyordo.shatback.dto.responses.timeEntry.TimeEntryShortResponse;
import com.fyordo.shatback.entities.TimeEntry;
import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.services.TaskService;
import com.fyordo.shatback.services.TimeEntryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/time-entry")
public class TimeEntryController extends BaseController {
    private final TaskService taskService;
    private final TimeEntryService timeEntryService;

    @GetMapping()
    public ResponseEntity<ListResponse<TimeEntryShortResponse>> getTimeEntries(
            @RequestParam Map<String, String> filter
            ) {
        List<TimeEntryShortResponse> timeEntries = timeEntryService.findAllForCurrentWeek()
                .stream().map((TimeEntry timeEntry) -> mapper.map(timeEntry, TimeEntryShortResponse.class)).toList();

        return ResponseEntity.ok(new ListResponse<>(timeEntries));
    }

    @GetMapping("/current")
    public ResponseEntity<TimeEntryResponse> getCurrentTimeEntry(
    ) {
        Optional<TimeEntry> currentTimeEntry = timeEntryService.getCurrentTimeEntry();
        if(currentTimeEntry.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(currentTimeEntry, TimeEntryResponse.class));
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntryResponse> updateTimeEntry(
            @PathVariable Long timeEntryId,
            TimeEntryUpdateRequest request
    ) {
        TimeEntry timeEntry = timeEntryService.findById(timeEntryId).orElseThrow(ModelNotFoundException::new);
        timeEntry = timeEntryService.updateTimeEntry(timeEntry, request);

        return ResponseEntity.ok(mapper.map(timeEntry, TimeEntryResponse.class));
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity<?> deleteTimeEntry(
            @PathVariable Long timeEntryId
    ) {
        timeEntryService.deleteTimeEntry(
                timeEntryService.findById(timeEntryId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }

    @PostMapping("/end/{timeEntryId}")
    public ResponseEntity<TimeEntryResponse> endTimeEntry(
            @PathVariable Long timeEntryId
    ) {
        TimeEntry timeEntry = timeEntryService.endTimeEntry(timeEntryId);
        return ResponseEntity.ok(mapper.map(timeEntry, TimeEntryResponse.class));
    }
}
