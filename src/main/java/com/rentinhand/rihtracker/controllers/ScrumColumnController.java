package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnCreateRequest;
import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnResponse;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/scrum-column")
@UserAuth
public class ScrumColumnController extends BaseController{
    private final ScrumColumnService scrumColumnService;

    @GetMapping("/{scrumColumnId}")
    public ResponseEntity<ScrumColumnResponse> getScrumColumnById(@PathVariable Long scrumColumnId) {
        Optional<ScrumColumn> scrumColumn = scrumColumnService.findById(scrumColumnId);
        return scrumColumn.map(value -> new ResponseEntity<>(new ScrumColumnResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    public ResponseEntity<List<ScrumColumnResponse>> getAllScrumColumns() {
        List<ScrumColumn> allScrumColumns = scrumColumnService.findAll();
        if (!allScrumColumns.isEmpty()) {
            List<ScrumColumnResponse> scrumColumnResponses = allScrumColumns.stream()
                    .map(ScrumColumnResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(scrumColumnResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ScrumColumnResponse> createScrumColumn(@RequestBody ScrumColumnCreateRequest scrumColumnCreateRequest) {
        ScrumColumn createdScrumColumn = scrumColumnService.createScrumColumn(scrumColumnCreateRequest);
        return new ResponseEntity<>(new ScrumColumnResponse(createdScrumColumn), HttpStatus.CREATED);
    }

    @PutMapping("/{scrumColumnId}")
    public ResponseEntity<ScrumColumnResponse> updateScrumColumn(@PathVariable Long scrumColumnId,
                                                 @RequestBody ScrumColumnUpdateRequest scrumColumnUpdateRequest) {
        Optional<ScrumColumn> existingScrumColumn = scrumColumnService.findById(scrumColumnId);
        if (existingScrumColumn.isPresent()) {
            ScrumColumn updatedScrumColumn = scrumColumnService.updateScrumColumn(existingScrumColumn.get(), scrumColumnUpdateRequest);
            return new ResponseEntity<>(new ScrumColumnResponse(updatedScrumColumn), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{scrumColumnId}")
    public ResponseEntity<Void> deleteScrumColumn(@PathVariable Long scrumColumnId) {
        Optional<ScrumColumn> scrumColumn = scrumColumnService.findById(scrumColumnId);
        if (scrumColumn.isPresent()) {
            scrumColumnService.deleteScrumColumn(scrumColumn.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
