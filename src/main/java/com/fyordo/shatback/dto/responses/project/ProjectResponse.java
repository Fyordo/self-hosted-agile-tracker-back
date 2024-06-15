package com.fyordo.shatback.dto.responses.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    protected Long id;
    protected String title;
    protected String avatar;
}
