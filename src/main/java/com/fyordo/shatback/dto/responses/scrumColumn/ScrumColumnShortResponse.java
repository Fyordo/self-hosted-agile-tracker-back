package com.fyordo.shatback.dto.responses.scrumColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrumColumnShortResponse {
    protected Long id;
    protected String title;
    protected String color;
}
