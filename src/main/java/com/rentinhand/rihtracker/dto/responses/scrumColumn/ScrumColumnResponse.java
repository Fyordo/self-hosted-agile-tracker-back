package com.rentinhand.rihtracker.dto.responses.scrumColumn;

import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrumColumnResponse {
    private Long id;

    private String title;

    private String color;

    public ScrumColumnResponse(ScrumColumn scrumColumn) {
        this.id = scrumColumn.getId();
        this.title = scrumColumn.getTitle();
        this.color = scrumColumn.getColor();
    }


}
