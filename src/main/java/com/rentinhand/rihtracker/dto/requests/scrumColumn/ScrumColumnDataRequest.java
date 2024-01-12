package com.rentinhand.rihtracker.dto.requests.scrumColumn;

import com.rentinhand.rihtracker.entities.ScrumColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public abstract class ScrumColumnDataRequest {
    private String title;
    private String color;

    public ScrumColumnDataRequest(ScrumColumn scrumColumn) {
        this.title = scrumColumn.getTitle();
        this.color = scrumColumn.getColor();

    }
}
