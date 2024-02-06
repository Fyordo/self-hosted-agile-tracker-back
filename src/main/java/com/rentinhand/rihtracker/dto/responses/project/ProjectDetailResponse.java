package com.rentinhand.rihtracker.dto.responses.project;

import com.rentinhand.rihtracker.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectDetailResponse extends ProjectResponse {
    // TODO List<Task>
}
